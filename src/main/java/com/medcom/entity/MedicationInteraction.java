package com.medcom.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "medication_interactions")
public class MedicationInteraction {
    @EmbeddedId
    private MedicationInteractionId id;

    private String description;

    // Getters and Setters
}
