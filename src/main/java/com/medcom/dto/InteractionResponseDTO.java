package com.medcom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InteractionResponseDTO {
    @JsonProperty("description")
    private String description;

    @JsonProperty("medications")
    private List<MedicationsConflictDTO> medications;

    @JsonProperty("alternatives")
    private List<String> medicationsAlternatives;
}
