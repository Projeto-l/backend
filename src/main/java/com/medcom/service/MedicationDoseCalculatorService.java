package com.medcom.service;

import com.medcom.dto.DoseCalculationRequestDTO;
import com.medcom.dto.DoseCalculationResponseDTO;
import com.medcom.entity.Drops;
import com.medcom.entity.Medication;
import com.medcom.entity.OralSuspension;
import com.medcom.entity.Presentation;
import com.medcom.entity.Tablet;
import com.medcom.repository.MedicationRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicationDoseCalculatorService {

    private final MedicationRepository medicationRepository;

    public MedicationDoseCalculatorService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    /**
     * Realiza o cálculo da dose para uma apresentação de medicação.
     * 
     * Para "mg/kg/dose":
     *   - Solução Oral: volume (mL) = (peso * dose padrão) / (mg/mL)
     *   - Comprimido: número de comprimidos = (peso * dose padrão) / (mg por comprimido)
     *   - Gotas: número de gotas = (peso * dose padrão) / (mg por gota)
     * Para "mg/kg/day":
     *   - Número de doses diárias = 24 / intervalo (horas)
     *   - Solução Oral: volume (mL) = [(peso * dose padrão) / dosesDiarias] / (mg/mL)
     *   - Comprimido: número de comprimidos = [(peso * dose padrão) / dosesDiarias] / (mg por comprimido)
     *   - Gotas: número de gotas = [(peso * dose padrão) / dosesDiarias] / (mg por gota)
     * 
     * Para comprimidos, o resultado é arredondado para o múltiplo de 0,25;
     * para gotas, para o número inteiro mais próximo.
     *
     * @param request DTO com os parâmetros: medicationId, presentationId,
     *                calculationType ("mg/kg/dose" ou "mg/kg/day"), standardDose, weight e interval.
     * @return DTO com a dose calculada e uma mensagem.
     */
    public DoseCalculationResponseDTO calculateDose(DoseCalculationRequestDTO request) {
        // Carrega o medicamento do banco de dados
        Medication medication = medicationRepository.findById(request.getMedicationId())
            .orElseThrow(() -> new IllegalArgumentException("Medication not found for ID: " + request.getMedicationId()));

        // Procura pela apresentação com o ID informado
        Presentation presentation = medication.getPresentations().stream()
            .filter(p -> p.getIdPresentation().equals(request.getPresentationId()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Presentation not found for ID: " + request.getPresentationId()));

        double calculatedDose;

        if (presentation instanceof OralSuspension) {
            OralSuspension oralSuspension = (OralSuspension) presentation;
            double concentration = oralSuspension.getMgPerMl(); // mg por mL da solução
            if ("mg/kg/dose".equalsIgnoreCase(request.getCalculationType())) {
                calculatedDose = (request.getWeight() * request.getStandardDose()) / concentration;
            } else if ("mg/kg/day".equalsIgnoreCase(request.getCalculationType())) {
                double dosesPerDay = 24.0 / request.getInterval();
                calculatedDose = ((request.getWeight() * request.getStandardDose()) / dosesPerDay) / concentration;
            } else {
                throw new IllegalArgumentException("Invalid calculation type: " + request.getCalculationType());
            }
        } else if (presentation instanceof Tablet) {
            Tablet tablet = (Tablet) presentation;
            double mgPerTablet = tablet.getMgPerTablet();
            if ("mg/kg/dose".equalsIgnoreCase(request.getCalculationType())) {
                calculatedDose = (request.getWeight() * request.getStandardDose()) / mgPerTablet;
            } else if ("mg/kg/day".equalsIgnoreCase(request.getCalculationType())) {
                double dosesPerDay = 24.0 / request.getInterval();
                calculatedDose = ((request.getWeight() * request.getStandardDose()) / dosesPerDay) / mgPerTablet;
            } else {
                throw new IllegalArgumentException("Invalid calculation type: " + request.getCalculationType());
            }
            // Arredonda para o múltiplo de 0,25 (ex.: 0,25; 0,50; 0,75; etc.)
            calculatedDose = Math.round(calculatedDose / 0.25) * 0.25;
        } else if (presentation instanceof Drops) {
            Drops drops = (Drops) presentation;
            // Para gotas, mg por gota = mgPerMl * mlPerDrop
            double mgPerDrop = drops.getMgPerMl() * drops.getMlPerDrop();
            if ("mg/kg/dose".equalsIgnoreCase(request.getCalculationType())) {
                calculatedDose = (request.getWeight() * request.getStandardDose()) / mgPerDrop;
            } else if ("mg/kg/day".equalsIgnoreCase(request.getCalculationType())) {
                double dosesPerDay = 24.0 / request.getInterval();
                calculatedDose = ((request.getWeight() * request.getStandardDose()) / dosesPerDay) / mgPerDrop;
            } else {
                throw new IllegalArgumentException("Invalid calculation type: " + request.getCalculationType());
            }
            // Para gotas, arredonda para o inteiro mais próximo
            calculatedDose = Math.round(calculatedDose);
        } else {
            throw new IllegalArgumentException("Presentation type not supported for dose calculation");
        }

        DoseCalculationResponseDTO response = new DoseCalculationResponseDTO();
        response.setCalculatedDose(calculatedDose);
        response.setMessage("Dose calculated successfully");
        return response;
    }
}
