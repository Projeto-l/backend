package com.medcom.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Drops extends Presentation {
    private double mgPerMl;
    private double mlPerDrop;

    @Override
    public String getInfo() {
        return mgPerMl + " mg/ml, " + mlPerDrop + " ml per drop";
    }

    @Override
    public double mgPerUnit() {
        return mgPerMl;
    }
}
