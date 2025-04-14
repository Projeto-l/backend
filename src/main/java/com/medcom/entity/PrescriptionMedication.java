package com.medcom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "prescription_medications")
@Data
public class PrescriptionMedication {
    @EmbeddedId
    private PrescriptionMedicationId id;

    @ManyToOne
    @MapsId("prescriptionId")
    @JsonBackReference
    @JoinColumn(name = "prescription_id")
    @ToString.Exclude
    private Prescription prescription;

    @ManyToOne
    @MapsId("medicationId")
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @Column(name = "notes")
    private String notes;

    @Column(name = "dosage")
    private BigDecimal dosage;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "duration")
    private Integer duration;
    
    @Column(name = "total_dose")
    private BigDecimal totalDose;

    public PrescriptionMedication() {
        this.id = new PrescriptionMedicationId(); 
    }

    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = new PrescriptionMedicationId();
        }
        if (prescription == null || prescription.getPrescriptionId() == null) {
            throw new IllegalStateException("Prescription must be set before persist");
        }
        if (medication == null || medication.getMedicationId() == null) {
            throw new IllegalStateException("Medication must be set before persist");
        }
        id.setPrescriptionId(prescription.getPrescriptionId());
        id.setMedicationId(medication.getMedicationId());
    }
}
