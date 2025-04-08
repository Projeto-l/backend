package com.medcom.controller;

import com.medcom.dto.PrescriptionDTO;
import com.medcom.dto.PrescriptionResponseDTO;
import com.medcom.dto.PresentationDTO;
import com.medcom.entity.Prescription;
import com.medcom.entity.Presentation;
import com.medcom.service.PrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/prescriptions")
@Tag(name = "Prescription Controller", description = "Endpoints for managing prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    @Operation(summary = "Get all prescriptions")
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get prescription by ID")
    public ResponseEntity<PrescriptionResponseDTO> getPrescriptionById(@PathVariable UUID id) {
        return ResponseEntity.ok(prescriptionService.findPrescriptionWhithInteractions(id));
    }

    @PostMapping
    @Operation(summary = "Create a new prescription")
    public ResponseEntity<PrescriptionResponseDTO> createPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        PrescriptionResponseDTO responseDTO = prescriptionService.createFromDTO(prescriptionDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a prescription")
    public ResponseEntity<PrescriptionResponseDTO> updatePrescription(@PathVariable UUID id, @RequestBody PrescriptionDTO prescriptionDTO) {
        PrescriptionResponseDTO updatedDTO = prescriptionService.updatePrescription(id, prescriptionDTO);
        return ResponseEntity.ok(updatedDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete prescription by ID")
    public ResponseEntity<Void> deletePrescription(@PathVariable UUID id) {
        prescriptionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
