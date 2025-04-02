package com.medcom.repository;

import com.medcom.entity.MedicationInteraction;
import com.medcom.entity.MedicationInteractionId;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface MedicationInteractionRepository extends JpaRepository<MedicationInteraction, MedicationInteractionId> {
    @Query("SELECT mi FROM MedicationInteraction mi WHERE "+
          "(mi.id.medication1Id = :med1Id AND mi.id.medication2Id = :med2Id) OR "+
          "(mi.id.medication1Id = :med2Id AND mi.id.medication2Id = :med1Id)")
    Optional<MedicationInteraction> findByMedicationInterationId(@Param("med1Id") UUID med1Id, @Param("med2Id") UUID med2Id);

}