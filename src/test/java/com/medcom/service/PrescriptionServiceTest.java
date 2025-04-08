package com.medcom.service;

import com.medcom.entity.Prescription;
import com.medcom.repository.PrescriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrescriptionServiceTest {
    /*      
    @Mock
    private PrescriptionRepository prescriptionRepository;

    @InjectMocks
    private PrescriptionService prescriptionService;

    private Prescription prescription;

    @BeforeEach
    void setUp() {
        prescription = new Prescription();
        prescription.setPrescriptionId(1);
        prescription.setNotes("Take every 6 hours");
    }

    @Test
    void shouldFindAllPrescriptions() {
        when(prescriptionRepository.findAll()).thenReturn(Arrays.asList(prescription));
        List<Prescription> prescriptions = prescriptionService.findAll();
        assertFalse(prescriptions.isEmpty());
    }

    @Test
    void shouldFindPrescriptionById() {
        when(prescriptionRepository.findById(prescription.getPrescriptionId())).thenReturn(Optional.of(prescription));
        Optional<Prescription> foundPrescription = prescriptionService.findById(prescription.getPrescriptionId());
        assertTrue(foundPrescription.isPresent());
        assertEquals(prescription.getNotes(), foundPrescription.get().getNotes());
    }

    @Test
    void shouldSavePrescription() {
        when(prescriptionRepository.save(prescription)).thenReturn(prescription);
        Prescription savedPrescription = prescriptionService.save(prescription);
        assertNotNull(savedPrescription);
        assertEquals(prescription.getNotes(), savedPrescription.getNotes());
    }

    @Test
    void shouldDeletePrescriptionById() {
        doNothing().when(prescriptionRepository).deleteById(prescription.getPrescriptionId());
        assertDoesNotThrow(() -> prescriptionService.deleteById(prescription.getPrescriptionId()));
        verify(prescriptionRepository, times(1)).deleteById(prescription.getPrescriptionId());
    }
    */
}
