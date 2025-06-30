package com.medcom.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class VerifyDoseRequestDTO {
    private UUID medicationId;
    private UUID presentationId;
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
        dto.setWeight(this.weight);
        dto.setInterval(this.interval);
        return dto;
    }
}
