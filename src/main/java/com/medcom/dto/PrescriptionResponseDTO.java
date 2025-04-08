package com.medcom.dto;

import com.medcom.entity.Prescription;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PrescriptionResponseDTO {
    private Prescription prescription;
    private List<InteractionPairDTO> interactions;
}
