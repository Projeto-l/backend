package com.medcom.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_preferences")
public class UserPreferences {
    @EmbeddedId
    private UserPreferencesId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("medicationId")
    private Medication medication;

}
