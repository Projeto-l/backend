package com.medcom.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Presentation {
    @Id
    @GeneratedValue
    private UUID idPresentation;

    @ManyToOne
    @JoinColumn(name = "medication_id", nullable = false)
    @JsonBackReference
    private Medication medication;

    public abstract String getInfo();

    public abstract double mgPerUnit();
}
