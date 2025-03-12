package com.medcom.service;

import com.medcom.dto.PrescriptionDTO;
import com.medcom.dto.PrescriptionMedicationDTO;
import com.medcom.entity.Medication;
import com.medcom.entity.User;
import com.medcom.entity.Prescription;
import com.medcom.entity.PrescriptionMedication;
import com.medcom.repository.MedicationRepository;
import com.medcom.repository.PrescriptionRepository;
import com.medcom.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicationRepository medicationRepository;
    private final UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(PrescriptionService.class);
    

    public PrescriptionService(PrescriptionRepository prescriptionRepository, MedicationRepository medicationRepository,
            UserRepository userRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.medicationRepository = medicationRepository;
        this.userRepository = userRepository;
    }

    public Prescription createFromDTO(PrescriptionDTO dto) {
        System.out.println("Received DTO: " + dto);
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("userId must not be null");
        }
        // Carrega o User (garante que ele existe e esteja gerenciado)
        User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User not found for ID: " + dto.getUserId()));
        System.out.println("Loaded User: " + user);
    
        Prescription prescription = new Prescription();
        prescription.setUser(user);
        prescription.setPatientName(dto.getPatientName());
        System.out.println("Created Prescription: " + prescription);
    
        if (dto.getItems() != null) {
            List<PrescriptionMedication> pmList = new ArrayList<>();
            for (PrescriptionMedicationDTO item : dto.getItems()) {
                System.out.println("Processing PrescriptionMedicationDTO: " + item);
                Medication managedMed = medicationRepository.findById(item.getMedicationId())
                    .orElseThrow(() -> new IllegalArgumentException("Medication not found for ID: " + item.getMedicationId()));
                System.out.println("Loaded Medication: " + managedMed);
    
                PrescriptionMedication pm = new PrescriptionMedication();
                pm.setPrescription(prescription);
                pm.setMedication(managedMed);
                pm.setNotes(item.getNotes());
                pm.setDosage(item.getDosage());
                pm.setFrequency(item.getFrequency());
                pm.setDuration(item.getDuration());
                System.out.println("Created PrescriptionMedication: " + pm);
                pmList.add(pm);
            }
            prescription.setPrescriptionMedications(pmList);
            System.out.println("Final Prescription Medications List: " + pmList);
        }
        Prescription saved = prescriptionRepository.save(prescription);
        System.out.println("Saved Prescription: " + saved);
        return saved;
    }
    

    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    public Prescription findById(UUID id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prescription not found for ID: " + id));
    }

    public void deleteById(UUID id) {
        prescriptionRepository.deleteById(id);
    }
}
