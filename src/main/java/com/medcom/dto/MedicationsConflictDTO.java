package com.medcom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MedicationsConflictDTO {
    @JsonProperty("medicationName")
    private String medicationName;
    @JsonProperty("thereIsConflict")
    private boolean thereIsConflict;
    
}
