package com.medcom.controller;

import com.medcom.entity.Medication;
import com.medcom.service.MedicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/medications")
@Tag(name = "Medication Controller", description = "Endpoints for managing medications")
public class MedicationController {
    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    @Operation(summary = "Get all medications")
    public ResponseEntity<List<Medication>> getAllMedications() {
        return ResponseEntity.ok(medicationService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get medication by ID")
    public ResponseEntity<Optional<Medication>> getMedicationById(@PathVariable UUID id) {
        return ResponseEntity.ok(medicationService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new medication")
    public ResponseEntity<Medication> createMedication(@RequestBody Medication medication) {
        return ResponseEntity.ok(medicationService.save(medication));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete medication by ID")
    public ResponseEntity<Void> deleteMedication(@PathVariable UUID id) {
        medicationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}