package com.medcom.repository;

import com.medcom.entity.FavoriteMedication;
import com.medcom.entity.FavoriteMedicationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteMedicationRepository extends JpaRepository<FavoriteMedication, FavoriteMedicationId> {
}
