package com.medcom.service;

import com.medcom.entity.Medication;
import com.medcom.repository.MedicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MedicationService {
    private final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> findAll() {
        return medicationRepository.findAll();
    }

    public Optional<Medication> findById(Integer id) {
        return medicationRepository.findById(id);
    }

    public Medication save(Medication medication) {
        return medicationRepository.save(medication);
    }

    public void deleteById(Integer id) {
        medicationRepository.deleteById(id);
    }
}
