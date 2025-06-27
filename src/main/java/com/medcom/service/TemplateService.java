package com.medcom.service;

import com.medcom.entity.Template;
import com.medcom.repository.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public Template createTemplate(Template template) {
        return templateRepository.save(template);
    }

    public List<Template> getAllTemplates() {
        return templateRepository.findAll();
    }

    public List<Template> getTemplatesByUserId(UUID userId) {
        return templateRepository.findByUserId(userId);
    }

    public Optional<Template> getTemplateById(UUID id) {
        return templateRepository.findById(id);
    }

    public Template updateTemplate(UUID id, Template updatedTemplate) {
        Template existingTemplate = templateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Template not found with id: " + id));

        existingTemplate.setName(updatedTemplate.getName());
        existingTemplate.setContent(updatedTemplate.getContent());
        existingTemplate.setFields(updatedTemplate.getFields());
        // O userId geralmente não é alterado, mas adicione se necessário

        return templateRepository.save(existingTemplate);
    }

    public void deleteTemplate(UUID id) {
        templateRepository.deleteById(id);
    }
}
