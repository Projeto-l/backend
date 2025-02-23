package com.medcom.repository;

import com.medcom.entity.MedicationInteraction;
import com.medcom.entity.MedicationInteractionId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicationInteractionRepository extends JpaRepository<MedicationInteraction, MedicationInteractionId> {
}