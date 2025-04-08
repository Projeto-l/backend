package com.medcom.service;

import com.medcom.dto.PresentationDTO;
import com.medcom.dto.PresentationType;
import com.medcom.entity.Presentation;
import com.medcom.entity.Tablet;
import com.medcom.entity.OralSuspension;
import com.medcom.entity.Drops;
import com.medcom.entity.Medication;
import com.medcom.repository.PresentationRepository;
import com.medcom.repository.MedicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PresentationService {

    private final PresentationRepository presentationRepository;
    private final MedicationRepository medicationRepository;

    public PresentationService(PresentationRepository presentationRepository, MedicationRepository medicationRepository) {
        this.presentationRepository = presentationRepository;
        this.medicationRepository = medicationRepository;
    }

    public Presentation createPresentation(PresentationDTO dto) {
        Medication medication = medicationRepository.findById(dto.getMedicationId())
            .orElseThrow(() -> new IllegalArgumentException("Medication not found for id: " + dto.getMedicationId()));
        Presentation presentation;
        if(dto.getPresentationType() == PresentationType.TABLET) {
            if(dto.getMgPerTablet() == null) {
                throw new IllegalArgumentException("mgPerTablet must be provided for TABLET type");
            }
            Tablet tablet = new Tablet();
            tablet.setMgPerTablet(dto.getMgPerTablet());
            presentation = tablet;
        } else if(dto.getPresentationType() == PresentationType.ORAL_SUSPENSION) {
            if(dto.getMgPerMl() == null) {
                throw new IllegalArgumentException("mgPerMl must be provided for ORAL_SUSPENSION type");
            }
            OralSuspension oralSuspension = new OralSuspension();
            oralSuspension.setMgPerMl(dto.getMgPerMl());
            presentation = oralSuspension;
        } else if(dto.getPresentationType() == PresentationType.DROPS) {
            if(dto.getMgPerMl() == null || dto.getMlPerDrop() == null) {
                throw new IllegalArgumentException("Both mgPerMl and mlPerDrop must be provided for DROPS type");
            }
            Drops drops = new Drops();
            drops.setMgPerMl(dto.getMgPerMl());
            drops.setMlPerDrop(dto.getMlPerDrop());
            presentation = drops;
        } else {
            throw new IllegalArgumentException("Unsupported presentation type: " + dto.getPresentationType());
        }
        presentation.setMedication(medication);
        return presentationRepository.save(presentation);
    }

    public List<Presentation> findAll() {
        return presentationRepository.findAll();
    }

    public Optional<Presentation> findById(UUID id) {
        return presentationRepository.findById(id);
    }

    public List<Presentation> getPresentationsByMedication(UUID medicationId) {
        return presentationRepository.findByMedication_MedicationId(medicationId);
    }

    public Presentation updatePresentation(UUID id, PresentationDTO dto) {
        Presentation existing = presentationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Presentation not found for id: " + id));
        Medication medication = medicationRepository.findById(dto.getMedicationId())
            .orElseThrow(() -> new IllegalArgumentException("Medication not found for id: " + dto.getMedicationId()));
        if(dto.getPresentationType() == PresentationType.TABLET && existing instanceof Tablet) {
            if(dto.getMgPerTablet() == null) {
                throw new IllegalArgumentException("mgPerTablet must be provided for TABLET type");
            }
            ((Tablet) existing).setMgPerTablet(dto.getMgPerTablet());
        } else if(dto.getPresentationType() == PresentationType.ORAL_SUSPENSION && existing instanceof OralSuspension) {
            if(dto.getMgPerMl() == null) {
                throw new IllegalArgumentException("mgPerMl must be provided for ORAL_SUSPENSION type");
            }
            ((OralSuspension) existing).setMgPerMl(dto.getMgPerMl());
        } else if(dto.getPresentationType() == PresentationType.DROPS && existing instanceof Drops) {
            if(dto.getMgPerMl() == null || dto.getMlPerDrop() == null) {
                throw new IllegalArgumentException("Both mgPerMl and mlPerDrop must be provided for DROPS type");
            }
            ((Drops) existing).setMgPerMl(dto.getMgPerMl());
            ((Drops) existing).setMlPerDrop(dto.getMlPerDrop());
        } else {
            throw new IllegalArgumentException("Presentation type mismatch or unsupported type");
        }
        existing.setMedication(medication);
        return presentationRepository.save(existing);
    }

    public void deleteById(UUID id) {
        presentationRepository.deleteById(id);
    }
}
