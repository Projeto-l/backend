package com.medcom.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "templates")
@Data
public class Template {

    @Id
    @GeneratedValue
    @Column(name = "template_id")
    private UUID templateId;

    private UUID userId;
    private String name;
    private String content;

    @ElementCollection
    @CollectionTable(name = "template_fields", joinColumns = @JoinColumn(name = "template_id"))
    @Column(name = "field")
    private List<String> fields;

}
