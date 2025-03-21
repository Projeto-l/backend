package com.medcom.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

@Embeddable
@Data
public class FavoriteMedicationId {
    private UUID userId;
    private UUID medicationId;
}
