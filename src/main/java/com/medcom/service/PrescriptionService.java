package com.medcom.service;

import com.medcom.entity.Prescription;
import com.medcom.repository.PrescriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    public Optional<Prescription> findById(Integer id) {
        return prescriptionRepository.findById(id);
    }

    public Prescription save(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public void deleteById(Integer id) {
        prescriptionRepository.deleteById(id);
    }
}
