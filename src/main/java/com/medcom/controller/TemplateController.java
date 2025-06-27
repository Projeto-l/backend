package com.medcom.controller;

import com.medcom.entity.Template;
import com.medcom.service.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    public ResponseEntity<Template> createTemplate(@RequestBody Template template) {
        Template savedTemplate = templateService.createTemplate(template);
        return ResponseEntity.ok(savedTemplate);
    }

    @GetMapping
    public ResponseEntity<List<Template>> getAllTemplates() {
        return ResponseEntity.ok(templateService.getAllTemplates());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Template>> getTemplatesByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(templateService.getTemplatesByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Template> getTemplateById(@PathVariable UUID id) {
        return templateService.getTemplateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Template> updateTemplate(@PathVariable UUID id, @RequestBody Template template) {
        Template updated = templateService.updateTemplate(id, template);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable UUID id) {
        templateService.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }
}
