package com.medcom.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PrescriptionMedicationDTO {
    private UUID medicationId;
    private String notes;
    private BigDecimal dosage;
    private String frequency;
    private Integer duration;
}
