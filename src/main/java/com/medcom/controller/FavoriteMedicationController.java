package com.medcom.controller;

import com.medcom.dto.FavoriteMedicationRequestDTO;
import com.medcom.service.FavoriteMedicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
@Tag(name = "Favorite Medication Controller", description = "Endpoints para favoritar e desfavoritar medicamentos")
public class FavoriteMedicationController {

    private final FavoriteMedicationService favoriteMedicationService;

    public FavoriteMedicationController(FavoriteMedicationService favoriteMedicationService) {
        this.favoriteMedicationService = favoriteMedicationService;
    }

    @PostMapping("/favorite")
    @Operation(summary = "Favoritar um medicamento para um usuário")
    public ResponseEntity<String> favoriteMedication(@RequestBody FavoriteMedicationRequestDTO dto) {
        favoriteMedicationService.favoriteMedication(dto);
        return ResponseEntity.ok("Medicamento favoritado com sucesso");
    }

    @DeleteMapping("/unfavorite")
    @Operation(summary = "Desfavoritar um medicamento para um usuário")
    public ResponseEntity<String> unfavoriteMedication(@RequestBody FavoriteMedicationRequestDTO dto) {
        favoriteMedicationService.unfavoriteMedication(dto);
        return ResponseEntity.ok("Medicamento desfavoritado com sucesso");
    }
}
