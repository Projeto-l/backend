package com.medcom.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favorite_medications")
@Data
public class FavoriteMedication {

    @EmbeddedId
    private FavoriteMedicationId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("medicationId")
    @JoinColumn(name = "medication_id")
    private Medication medication;

    public FavoriteMedication() {
        this.id = new FavoriteMedicationId();
    }

    
}
