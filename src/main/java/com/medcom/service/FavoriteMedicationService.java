package com.medcom.service;

import com.medcom.dto.FavoriteMedicationRequestDTO;
import com.medcom.entity.FavoriteMedication;
import com.medcom.entity.FavoriteMedicationId;
import com.medcom.entity.Medication;
import com.medcom.entity.User;
import com.medcom.repository.FavoriteMedicationRepository;
import com.medcom.repository.MedicationRepository;
import com.medcom.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FavoriteMedicationService {

    private final FavoriteMedicationRepository favoriteMedicationRepository;
    private final UserRepository userRepository;
    private final MedicationRepository medicationRepository;

    public FavoriteMedicationService(FavoriteMedicationRepository favoriteMedicationRepository,
                                     UserRepository userRepository,
                                     MedicationRepository medicationRepository) {
        this.favoriteMedicationRepository = favoriteMedicationRepository;
        this.userRepository = userRepository;
        this.medicationRepository = medicationRepository;
    }

    /**
     * Adiciona um medicamento à lista de favoritos do usuário.
     */
    public void favoriteMedication(FavoriteMedicationRequestDTO dto) {
        // Verifica se o usuário existe
        User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID: " + dto.getUserId()));

        // Verifica se o medicamento existe
        Medication medication = medicationRepository.findById(dto.getMedicationId())
            .orElseThrow(() -> new IllegalArgumentException("Medicamento não encontrado para o ID: " + dto.getMedicationId()));

        // Cria a chave composta
        FavoriteMedicationId id = new FavoriteMedicationId();
        id.setUserId(user.getUserId());
        id.setMedicationId(medication.getMedicationId());

        // Se ainda não estiver favoritado, cria o registro
        if (!favoriteMedicationRepository.existsById(id)) {
            FavoriteMedication favorite = new FavoriteMedication();
            favorite.setId(id);
            favorite.setUser(user);
            favorite.setMedication(medication);
            favoriteMedicationRepository.save(favorite);
        }
    }

    /**
     * Remove um medicamento da lista de favoritos do usuário.
     */
    public void unfavoriteMedication(FavoriteMedicationRequestDTO dto) {
        FavoriteMedicationId id = new FavoriteMedicationId();
        id.setUserId(dto.getUserId());
        id.setMedicationId(dto.getMedicationId());

        if (favoriteMedicationRepository.existsById(id)) {
            favoriteMedicationRepository.deleteById(id);
        }
    }
}
