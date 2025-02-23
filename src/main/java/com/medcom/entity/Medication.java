package com.medcom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "medications")
@Data
public class Medication {
    @Id
    @GeneratedValue
    private UUID medicationId;

    private String name;
    private String presentation;
    private double standardDosage;

}