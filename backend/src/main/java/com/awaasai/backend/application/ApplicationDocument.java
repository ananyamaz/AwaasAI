package com.awaasai.backend.application;

import jakarta.persistence.*;

@Entity
@Table(name = "application_documents")
public class ApplicationDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_id", nullable = false)
    private Long applicationId;

    @Column(name = "requirement_id", nullable = false)
    private Long requirementId;

    @Column(name = "status", nullable = false)
    private String status;

    public ApplicationDocument() {
    }

    public Long getId() {
        return id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public Long getRequirementId() {
        return requirementId;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}