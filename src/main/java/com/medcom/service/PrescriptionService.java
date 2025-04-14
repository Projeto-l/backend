package com.medcom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.medcom.dto.AddMedicationToPrescriptionDTO;
import com.medcom.dto.InteractionPairDTO;
import com.medcom.dto.PrescriptionDTO;
import com.medcom.dto.PrescriptionMedicationDTO;
import com.medcom.dto.PrescriptionResponseDTO;
import com.medcom.entity.Medication;
import com.medcom.entity.Prescription;
import com.medcom.entity.PrescriptionMedication;
import com.medcom.entity.User;
import com.medcom.repository.MedicationRepository;
import com.medcom.repository.PrescriptionRepository;
import com.medcom.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicationRepository medicationRepository;
    private final UserRepository userRepository;
    private final MedicationInteractionService medicationInteractionService;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
            MedicationRepository medicationRepository,
            UserRepository userRepository,
            MedicationInteractionService medicationInteractionService) {
        this.prescriptionRepository = prescriptionRepository;
        this.medicationRepository = medicationRepository;
        this.userRepository = userRepository;
        this.medicationInteractionService = medicationInteractionService;
    }

    public PrescriptionResponseDTO createFromDTO(PrescriptionDTO dto) {
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("userId must not be null");
        }
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found for ID: " + dto.getUserId()));

        Prescription prescription = new Prescription();
        prescription.setUser(user);
        prescription.setPatientName(dto.getPatientName());

        if (dto.getItems() != null) {
            List<PrescriptionMedication> pmList = new ArrayList<>();
            for (PrescriptionMedicationDTO item : dto.getItems()) {
                Medication managedMed = medicationRepository.findById(item.getMedicationId())
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Medication not found for ID: " + item.getMedicationId()));
                PrescriptionMedication pm = new PrescriptionMedication();
                pm.setPrescription(prescription);
                pm.setMedication(managedMed);
                pm.setNotes(item.getNotes());
                pm.setDosage(item.getDosage());
                pm.setFrequency(item.getFrequency());
                pm.setDuration(item.getDuration());
                pm.setTotalDose(item.getTotalDose());
                pmList.add(pm);
            }
            prescription.setPrescriptionMedications(pmList);
            System.out.println("Final Prescription Medications List: " + pmList);
        }
        Prescription saved = prescriptionRepository.save(prescription);

        List<Medication> medicationsUsed = saved.getPrescriptionMedications().stream()
                .map(PrescriptionMedication::getMedication)
                .distinct()
                .collect(Collectors.toList());

        List<InteractionPairDTO> interactions = medicationInteractionService.findInteractions(medicationsUsed);
        System.out.println("Found interactions: " + interactions);

        return new PrescriptionResponseDTO(saved, interactions);
    }

    public PrescriptionResponseDTO updatePrescription(UUID prescriptionID, PrescriptionDTO prescriptionDTO) {
        Prescription prescriptionEdited = findById(prescriptionID);

        List<PrescriptionMedication> prescriptionMedications = prescriptionEdited.getPrescriptionMedications();
        List<PrescriptionMedication> updatedMedications = new ArrayList<>();

        for (PrescriptionMedicationDTO prescription : prescriptionDTO.getItems()) {
            PrescriptionMedication existingMedication = prescriptionMedications.stream()
                    .filter(pm -> pm.getMedication().getMedicationId().equals(prescription.getMedicationId()))
                    .findFirst()
                    .orElse(null);

            if (existingMedication != null) {
                existingMedication.setDosage(prescription.getDosage());
                existingMedication.setFrequency(prescription.getFrequency());
                existingMedication.setDuration(prescription.getDuration());
                existingMedication.setNotes(prescription.getNotes());
                updatedMedications.add(existingMedication);
            }
        }

        prescriptionMedications.removeIf(pm -> updatedMedications.stream().noneMatch(
                updated -> updated.getMedication().getMedicationId().equals(pm.getMedication().getMedicationId())));

        if (updatedMedications.isEmpty()) {
            deleteById(prescriptionID);
            return new PrescriptionResponseDTO(null, List.of());
        }

        prescriptionEdited.getPrescriptionMedications().clear();
        prescriptionEdited.getPrescriptionMedications().addAll(updatedMedications);

        Prescription saved = prescriptionRepository.save(prescriptionEdited);

        List<Medication> medications = saved.getPrescriptionMedications().stream()
                .map(PrescriptionMedication::getMedication)
                .distinct()
                .collect(Collectors.toList());

        List<InteractionPairDTO> interactions = medicationInteractionService.findInteractions(medications);

        return new PrescriptionResponseDTO(saved, interactions);

    }

    public PrescriptionResponseDTO addMedicationToPrescription(UUID prescriptionId,
            AddMedicationToPrescriptionDTO addMedicationToPrescriptionDTO) {
        Prescription prescription = findById(prescriptionId);

        Medication medication = medicationRepository.findById(addMedicationToPrescriptionDTO.getMedicationId())
                .orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado."));

        boolean alreadyExists = prescription.getPrescriptionMedications().stream()
                .anyMatch(pm -> pm.getMedication().getMedicationId().equals(medication.getMedicationId()));

        if (alreadyExists) {
            throw new IllegalArgumentException("Este medicamento já está presente na prescrição.");
        }

        PrescriptionMedication prescriptionMedication = new PrescriptionMedication();
        prescriptionMedication.setPrescription(prescription);
        prescriptionMedication.setMedication(medication);
        prescriptionMedication.setDosage(addMedicationToPrescriptionDTO.getDosage());
        prescriptionMedication.setFrequency(addMedicationToPrescriptionDTO.getFrequency());
        prescriptionMedication.setDuration(addMedicationToPrescriptionDTO.getDuration());
        prescriptionMedication.setNotes(addMedicationToPrescriptionDTO.getNotes());
        prescriptionMedication.setTotalDose(addMedicationToPrescriptionDTO.getTotalDose());

        prescription.getPrescriptionMedications().add(prescriptionMedication);

        Prescription saved = prescriptionRepository.save(prescription);

        List<Medication> medications = saved.getPrescriptionMedications().stream()
                .map(PrescriptionMedication::getMedication)
                .distinct()
                .collect(Collectors.toList());

        List<InteractionPairDTO> interactions = medicationInteractionService.findInteractions(medications);

        return new PrescriptionResponseDTO(saved, interactions);

    }

    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    public Prescription findById(UUID id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prescription not found for ID: " + id));
    }

    public PrescriptionResponseDTO findPrescriptionWhithInteractions(UUID id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prescription not found for ID: " + id));

        List<Medication> medicationsUsed = prescription.getPrescriptionMedications().stream()
                .map(PrescriptionMedication::getMedication)
                .distinct()
                .collect(Collectors.toList());

        List<InteractionPairDTO> interactions = medicationInteractionService.findInteractions(medicationsUsed);

        return new PrescriptionResponseDTO(prescription, interactions);
    }

    public void deleteById(UUID id) {
        prescriptionRepository.deleteById(id);
    }
}
