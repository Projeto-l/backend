package com.medcom.repository;

import com.medcom.entity.PrescriptionMedication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrescriptionMedicationRepository extends JpaRepository<PrescriptionMedication, Integer> {
}