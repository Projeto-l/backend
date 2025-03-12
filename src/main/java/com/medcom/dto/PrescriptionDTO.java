package com.medcom.dto;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class PrescriptionDTO {
    private UUID userId; 
    private String patientName;
    private List<PrescriptionMedicationDTO> items;
}
    