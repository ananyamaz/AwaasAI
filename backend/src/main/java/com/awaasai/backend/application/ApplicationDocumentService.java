package com.awaasai.backend.application;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationDocumentService {

    private final ApplicationDocumentRepository documentRepository;
    private final DocumentRequirementRepository requirementRepository;

    public ApplicationDocumentService(
            ApplicationDocumentRepository documentRepository,
            DocumentRequirementRepository requirementRepository) {

        this.documentRepository = documentRepository;
        this.requirementRepository = requirementRepository;
    }

    public List<MissingDocumentResponse> getMissingDocuments(
            Long applicationId) {

        List<ApplicationDocument> missingDocuments =
                documentRepository.findByApplicationIdAndStatus(
                        applicationId,
                        "MISSING");

        return missingDocuments.stream()
                .map(document -> {

                    DocumentRequirement requirement =
                            requirementRepository
                                    .findById(document.getRequirementId())
                                    .orElseThrow();

                    return new MissingDocumentResponse(
                            requirement.getDocumentName(),
                            requirement.getDescription(),
                            requirement.getMandatory()
                    );
                })
                .toList();
    }
}