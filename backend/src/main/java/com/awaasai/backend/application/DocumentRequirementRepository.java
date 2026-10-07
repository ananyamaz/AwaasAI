package com.awaasai.backend.application;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRequirementRepository
        extends JpaRepository<DocumentRequirement, Long> {
}