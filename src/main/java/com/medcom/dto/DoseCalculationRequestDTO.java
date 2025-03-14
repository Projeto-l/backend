package com.medcom.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class DoseCalculationRequestDTO {
    private UUID medicationId;       // ID do medicamento
    private UUID presentationId;     // ID da apresentação do medicamento
    private String calculationType;  // "mg/kg/dose" ou "mg/kg/day"
    private double standardDose;     // Dose padrão (mg/kg) – interpretação conforme o tipo de cálculo
    private double weight;           // Peso do paciente em kg
    private int interval;            // Intervalo entre doses (horas) – usado se o cálculo for mg/kg/day
}
