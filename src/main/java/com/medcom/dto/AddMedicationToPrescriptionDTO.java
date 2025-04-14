package com.medcom.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AddMedicationToPrescriptionDTO {
    private UUID medicationId;
    private BigDecimal dosage;
    private String frequency;
    private Integer duration;
    private String notes;
    private BigDecimal totalDose;
    
}