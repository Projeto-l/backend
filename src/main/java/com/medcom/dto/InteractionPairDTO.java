package com.medcom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class InteractionPairDTO {
    private UUID medication1Id;
    private String medication1Name;
    private UUID medication2Id;
    private String medication2Name;
    private String description;
    private String medicationsAlternatives;
}
