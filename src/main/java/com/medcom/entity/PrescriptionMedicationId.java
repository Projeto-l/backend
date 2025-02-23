package com.medcom.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

@Embeddable
@Data
public class PrescriptionMedicationId {
    private UUID prescriptionId;

    private UUID medicationId;

}