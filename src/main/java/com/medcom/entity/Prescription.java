package com.medcom.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "prescriptions")
@Data
public class Prescription {
    @Id
    @GeneratedValue
    private Integer prescriptionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String notes;

}
