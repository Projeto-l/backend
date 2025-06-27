package com.medcom.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class VerifyDoseRequestDTO {
    private UUID medicationId;
    private UUID presentationId;
    private String calculationType; // "mg/kg/dose" ou "mg/kg/day"
    private double standardDose;
    private double weight;
    private int interval;
    private double takenDose; // Dose que o paciente está tomando

    /**
     * Conversão para reuso no método calculateDose
     */
    public DoseCalculationRequestDTO toDoseCalculationRequestDTO() {
        DoseCalculationRequestDTO dto = new DoseCalculationRequestDTO();
        dto.setMedicationId(this.medicationId);
        dto.setPresentationId(this.presentationId);
        dto.setCalculationType(this.calculationType);
        dto.setStandardDose(this.standardDose);
        dto.setWeight(this.weight);
        dto.setInterval(this.interval);
        return dto;
    }
}
