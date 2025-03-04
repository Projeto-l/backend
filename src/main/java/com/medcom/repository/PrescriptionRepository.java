package com.medcom.repository;

import com.medcom.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
}
