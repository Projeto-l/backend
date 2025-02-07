package com.medcom.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "prescription_medications")
@Data
public class PrescriptionMedication {
    @EmbeddedId
    private PrescriptionMedicationId id;

    @ManyToOne
    @MapsId("prescriptionId")
    private Prescription prescription;

    @ManyToOne
    @MapsId("medicationId")
    private Medication medication;

    private double dosage;
    private String frequency;
    private String duration;


}