package com.awaasai.backend.application;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficerRecommendationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationDocumentService documentService;

    public OfficerRecommendationService(
            ApplicationRepository applicationRepository,
            ApplicationDocumentService documentService) {

        this.applicationRepository = applicationRepository;
        this.documentService = documentService;
    }

    public OfficerRecommendationResponse getRecommendation(
            Long applicationId) {

        Application application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found"));

        List<MissingDocumentResponse> missingDocuments =
                documentService.getMissingDocuments(applicationId);

        List<String> missingDocumentNames =
                missingDocuments.stream()
                        .map(MissingDocumentResponse::getDocumentName)
                        .toList();

        String recommendation;

        if (missingDocumentNames.isEmpty()) {
            recommendation =
                    "All required documents have been submitted. "
                    + "The application can proceed to the next verification stage.";
        } else {
            recommendation =
                    "Request the missing documents from the applicant "
                    + "before proceeding with verification.";
        }

        return new OfficerRecommendationResponse(
                application.getId(),
                application.getCustomerName(),
                application.getApplicationStatus(),
                missingDocumentNames,
                recommendation
        );
    }
}