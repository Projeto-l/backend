package com.medcom.dto;

import lombok.Data;

@Data
public class VerifyDoseResponseDTO {
    private boolean isCorrect;
    private double expectedDose;
    private double takenDose;
    private String message;
}
