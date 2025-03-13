package com.medcom.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "medication_interactions")
public class MedicationInteraction {
    @EmbeddedId
    private MedicationInteractionId id;

    private String description;
}
