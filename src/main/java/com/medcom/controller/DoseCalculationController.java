package com.medcom.controller;

import com.medcom.dto.DoseCalculationRequestDTO;
import com.medcom.dto.DoseCalculationResponseDTO;
import com.medcom.service.MedicationDoseCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DoseCalculationController {

    private final MedicationDoseCalculatorService doseCalculatorService;

    public DoseCalculationController(MedicationDoseCalculatorService doseCalculatorService) {
        this.doseCalculatorService = doseCalculatorService;
    }

    @PostMapping("/dose-calculation")
    public ResponseEntity<DoseCalculationResponseDTO> calculateDose(@RequestBody DoseCalculationRequestDTO request) {
        DoseCalculationResponseDTO response = doseCalculatorService.calculateDose(request);
        return ResponseEntity.ok(response);
    }
}
