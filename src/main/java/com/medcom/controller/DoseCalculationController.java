package com.medcom.controller;

import com.medcom.dto.DoseCalculationRequestDTO;
import com.medcom.dto.DoseCalculationResponseDTO;
import com.medcom.dto.VerifyDoseRequestDTO;
import com.medcom.dto.VerifyDoseResponseDTO;
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

    @PostMapping("/verify-dose")
    public ResponseEntity<VerifyDoseResponseDTO> verifyDose(@RequestBody VerifyDoseRequestDTO request) {
        VerifyDoseResponseDTO response = doseCalculatorService.verifyDose(request);
        return ResponseEntity.ok(response);
    }

}
