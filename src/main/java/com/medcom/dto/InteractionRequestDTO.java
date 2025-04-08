package com.medcom.dto;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class InteractionRequestDTO {
    private String medication1;
    private String medication2;
    
}
