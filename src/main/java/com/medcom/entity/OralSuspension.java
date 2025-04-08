package com.medcom.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class OralSuspension extends Presentation {
    private double mgPerMl;

    @Override
    public String getInfo() {
        return mgPerMl + " mg/ml";
    }

    @Override
    public double mgPerUnit() {
        return mgPerMl;
    }
}
