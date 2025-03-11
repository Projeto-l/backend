package com.medcom.dto;

import java.util.UUID;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class PresentationDTO {
    @NotNull
    private UUID medicationId;

    @NotNull
    private PresentationType presentationType;
    private Double mgPerTablet;
    private Double mgPerMl;
    private Double mlPerDrop;
}
