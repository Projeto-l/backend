package com.medcom.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Tablet extends Presentation {
    private double mgPerTablet;

    @Override
    public String getInfo() {
        return mgPerTablet + " mg per tablet";
    }

    @Override
    public double mgPerUnit() {
        return mgPerTablet;
    }
}
