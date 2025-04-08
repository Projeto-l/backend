package com.medcom.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "medications")
@Data
public class Medication {
    @Id
    @GeneratedValue
    @Column(name = "medication_id")
    private UUID medicationId;

    private String name;

    private Double defaultDosePerDay;

    private Double defaultDosePerAdministration;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "medication_id")
    @JsonManagedReference
    private List<Presentation> presentations;

}
