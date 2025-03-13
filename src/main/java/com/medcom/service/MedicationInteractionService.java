package com.medcom.service;

import com.medcom.dto.InteractionPairDTO;
import com.medcom.entity.Medication;
import com.medcom.entity.MedicationInteraction;
import com.medcom.repository.MedicationInteractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationInteractionService {

    private final MedicationInteractionRepository interactionRepository;

    public MedicationInteractionService(MedicationInteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    /**
     * Dada uma lista de medicamentos, retorna uma lista de interações entre eles, em formato de DTO.
     *
     * @param medications Lista de medicamentos para verificar interações.
     * @return Lista de InteractionPairDTO que representam interações entre os medicamentos fornecidos.
     */
    public List<InteractionPairDTO> findInteractions(List<Medication> medications) {
        if (medications == null || medications.size() < 2) {
            return List.of();
        }

        // Cria um mapa para associar cada medicationId ao objeto Medication
        Map<UUID, Medication> medicationMap = medications.stream()
                .collect(Collectors.toMap(Medication::getMedicationId, med -> med));

        // Cria um conjunto com os IDs dos medicamentos (como strings) para facilitar a comparação
        Set<String> medicationIdsStr = medications.stream()
                .map(med -> med.getMedicationId().toString())
                .collect(Collectors.toSet());

        List<MedicationInteraction> allInteractions = interactionRepository.findAll();
        return allInteractions.stream()
                .filter(interaction -> {
                    String id1 = interaction.getId().getMedication1Id().toString();
                    String id2 = interaction.getId().getMedication2Id().toString();
                    return medicationIdsStr.contains(id1) && medicationIdsStr.contains(id2);
                })
                .map(interaction -> {
                    UUID med1Id = interaction.getId().getMedication1Id();
                    UUID med2Id = interaction.getId().getMedication2Id();
                    String med1Name = medicationMap.get(med1Id).getName();
                    String med2Name = medicationMap.get(med2Id).getName();
                    return new InteractionPairDTO(med1Id, med1Name, med2Id, med2Name, interaction.getDescription());
                })
                .collect(Collectors.toList());
    }
}
