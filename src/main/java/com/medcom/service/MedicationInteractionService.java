package com.medcom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.medcom.dto.InteractionPairDTO;
import com.medcom.dto.InteractionRequestDTO;

import com.medcom.dto.InteractionResponseDTO;
import com.medcom.entity.Medication;
import com.medcom.entity.MedicationInteraction;
import com.medcom.entity.MedicationInteractionId;
import com.medcom.repository.MedicationInteractionRepository;
import java.util.Optional;

import org.springframework.http.*;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class MedicationInteractionService {

    private final MedicationInteractionRepository interactionRepository;
    private final WebClient webClient;

    public MedicationInteractionService(MedicationInteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
        this.webClient = WebClient.create();
    }

    /**
     * Dada uma lista de medicamentos, retorna uma lista de interações entre eles,
     * em formato de DTO.
     *
     * @param medications Lista de medicamentos para verificar interações.
     * @return Lista de InteractionPairDTO que representam interações entre os
     *         medicamentos fornecidos.
     */
    public List<InteractionPairDTO> findInteractions(List<Medication> medications) {
        if (medications == null || medications.size() < 2) {
            return List.of();
        }

        List<InteractionPairDTO> interactionDTOs = new ArrayList<>();

        for (int i = 0; i < medications.size(); i++) {
            for (int j = i + 1; j < medications.size(); j++) {
                Medication med1 = medications.get(i);
                Medication med2 = medications.get(j);

                Optional<MedicationInteraction> interactionOpt = interactionRepository
                        .findByMedicationInterationId(med1.getMedicationId(), med2.getMedicationId());

                MedicationInteraction interaction;

                if (interactionOpt.isPresent()) {
                    interaction = interactionOpt.get(); 
                } else {
                    try {
                        interaction = checkInteractionRequest(med1, med2); 
                    } catch (Exception e) {
                        System.err.println("Erro ao buscar interação para " + med1.getName() + " e " + med2.getName()
                                + ": " + e.getMessage());
                        continue; 
                    }
                }

                interactionDTOs.add(new InteractionPairDTO(
                        interaction.getId().getMedication1Id(),
                        med1.getName(),
                        interaction.getId().getMedication2Id(),
                        med2.getName(),
                        interaction.getDescription()));
            }
        }
        return interactionDTOs;
    }
    
    private MedicationInteraction checkInteractionRequest(Medication medication1, Medication medication2) {
        try {
            InteractionRequestDTO requestDto = new InteractionRequestDTO(medication1.getName(), medication2.getName());
            InteractionResponseDTO response = sendInteractionRequest(requestDto);

            return saveInteraction(response, medication1, medication2);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar interação entre medicamentos: " + e.getMessage(), e);
        }
    }

    private InteractionResponseDTO sendInteractionRequest(InteractionRequestDTO requestBody) {
        try {
            return webClient.post()
                    .uri("http://host.docker.internal:8000/api/v1/check-interaction")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(InteractionResponseDTO.class)
                    .block();
        }catch (Exception e) {
            throw new RuntimeException("Erro ao enviar requisição para API de interação", e);
        }
    }

    private MedicationInteraction saveInteraction(InteractionResponseDTO response, Medication medication1, Medication medication2) {
        boolean thereIsConflict = response.getMedications().get(0).isThereIsConflict() &&
                                  response.getMedications().get(1).isThereIsConflict();
        String description = response.getDescription();

        MedicationInteractionId interactionId = new MedicationInteractionId();
        interactionId.setMedication1Id(medication1.getMedicationId());
        interactionId.setMedication2Id(medication2.getMedicationId());

        MedicationInteraction interaction = new MedicationInteraction();
        interaction.setId(interactionId);
        interaction.setThereIsConflict(thereIsConflict);
        interaction.setDescription(description);

        return interactionRepository.save(interaction);
    }

}
