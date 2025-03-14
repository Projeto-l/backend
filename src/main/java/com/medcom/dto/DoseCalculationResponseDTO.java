package com.medcom.dto;

import lombok.Data;

@Data
public class DoseCalculationResponseDTO {
    private double calculatedDose;  // Dose calculada para cada administração
    private String message;         // Mensagem adicional, se necessário
}
