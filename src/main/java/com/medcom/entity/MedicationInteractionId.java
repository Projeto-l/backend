package com.medcom.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

@Embeddable
@Data
public class MedicationInteractionId {
    private UUID medication1Id;
    private UUID medication2Id;

}
