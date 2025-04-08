package com.medcom.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class FavoriteMedicationRequestDTO {
    private UUID userId;
    private UUID medicationId;
}
