package com.medcom.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RecoverPasswordRequestDTO(
        @NotBlank(message = "Email is required to recover password")
        String email) {
}
