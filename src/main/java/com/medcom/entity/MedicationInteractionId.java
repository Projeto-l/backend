package com.medcom.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

@Embeddable
@Data
public class MedicationInteractionId {
    private Integer medication1Id;
    private Integer medication2Id;

}
