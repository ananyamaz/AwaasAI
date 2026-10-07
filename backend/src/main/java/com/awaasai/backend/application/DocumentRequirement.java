package com.awaasai.backend.application;

import jakarta.persistence.*;

@Entity
@Table(name = "document_requirements")
public class DocumentRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_name", nullable = false)
    private String documentName;

    @Column(name = "description")
    private String description;

    @Column(name = "mandatory")
    private Boolean mandatory;

    public DocumentRequirement() {
    }

    public Long getId() {
        return id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }
}