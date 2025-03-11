package com.medcom.controller;

import com.medcom.dto.PresentationDTO;
import com.medcom.entity.Presentation;
import com.medcom.service.PresentationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/presentations")
@Tag(name = "Presentation Controller", description = "Endpoints for managing presentations")
public class PresentationController {

    private final PresentationService presentationService;

    public PresentationController(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @PostMapping
    @Operation(summary = "Create a new presentation")
    public ResponseEntity<Presentation> createPresentation(@RequestBody PresentationDTO presentationDTO) {
        Presentation presentation = presentationService.createPresentation(presentationDTO);
        return ResponseEntity.ok(presentation);
    }

    @GetMapping
    @Operation(summary = "Get all presentations")
    public ResponseEntity<List<Presentation>> getAllPresentations() {
        List<Presentation> presentations = presentationService.findAll();
        return ResponseEntity.ok(presentations);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get presentation by id")
    public ResponseEntity<Presentation> getPresentationById(@PathVariable UUID id) {
        Optional<Presentation> presentationOpt = presentationService.findById(id);
        return presentationOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/medication/{medicationId}")
    @Operation(summary = "Get presentations by medication id")
    public ResponseEntity<List<Presentation>> getPresentationsByMedication(@PathVariable UUID medicationId) {
        List<Presentation> presentations = presentationService.getPresentationsByMedication(medicationId);
        return ResponseEntity.ok(presentations);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a presentation")
    public ResponseEntity<Presentation> updatePresentation(@PathVariable UUID id, @RequestBody PresentationDTO presentationDTO) {
        Presentation updated = presentationService.updatePresentation(id, presentationDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a presentation")
    public ResponseEntity<Void> deletePresentation(@PathVariable UUID id) {
        presentationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
