package com.medcom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

@Data
@Embeddable
public class MedicationInteractionId {
    @Column(name = "medication1_id")
    private UUID medication1Id;
    @Column(name = "medication2_id")
    private UUID medication2Id;
}
