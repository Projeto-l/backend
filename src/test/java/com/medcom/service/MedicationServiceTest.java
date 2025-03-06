package com.medcom.service;

import com.medcom.entity.Medication;
import com.medcom.repository.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public @ExtendWith(MockitoExtension.class)
 class MedicationServiceTest {
    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private MedicationService medicationService;

    private Medication medication;

    @BeforeEach
    void setUp() {
        medication = new Medication();
        medication.setMedicationId(UUID.randomUUID());
        medication.setName("Paracetamol");
    }

    @Test
    void shouldFindAllMedications() {
        when(medicationRepository.findAll()).thenReturn(Arrays.asList(medication));
        List<Medication> medications = medicationService.findAll();
        assertFalse(medications.isEmpty());
    }

    @Test
    void shouldSaveMedication() {
        when(medicationRepository.save(medication)).thenReturn(medication);
        Medication savedMedication = medicationService.save(medication);
        assertNotNull(savedMedication);
        assertEquals(medication.getName(), savedMedication.getName());
    }
}

