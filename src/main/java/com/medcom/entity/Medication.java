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

    public void addPresentation(Presentation presentation) {
        presentations.add(presentation);
    }

    public void removePresentation(Presentation presentation) {
        presentations.remove(presentation);
    }

    public void listPresentations() {
        for (Presentation presentation : presentations) {
            System.out.println("Presentation: " + presentation.getInfo() + ", Mg per unit: " + presentation.mgPerUnit());
        }
    }
}
