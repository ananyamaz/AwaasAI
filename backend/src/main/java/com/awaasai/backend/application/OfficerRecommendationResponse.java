package com.awaasai.backend.application;

import java.util.List;

public class OfficerRecommendationResponse {

    private Long applicationId;
    private String customerName;
    private String applicationStatus;
    private List<String> missingDocuments;
    private String recommendation;

    public OfficerRecommendationResponse(
            Long applicationId,
            String customerName,
            String applicationStatus,
            List<String> missingDocuments,
            String recommendation) {

        this.applicationId = applicationId;
        this.customerName = customerName;
        this.applicationStatus = applicationStatus;
        this.missingDocuments = missingDocuments;
        this.recommendation = recommendation;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public List<String> getMissingDocuments() {
        return missingDocuments;
    }

    public String getRecommendation() {
        return recommendation;
    }
}