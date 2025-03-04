package com.medcom.entity;


import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

@Embeddable
@Data
public class UserPreferencesId {
    private Integer userId;
    private Integer medicationId;
}
