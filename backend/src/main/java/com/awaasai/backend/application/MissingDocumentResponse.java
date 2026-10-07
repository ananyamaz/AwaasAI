package com.awaasai.backend.application;

public class MissingDocumentResponse {

    private String documentName;
    private String description;
    private Boolean mandatory;

    public MissingDocumentResponse(
            String documentName,
            String description,
            Boolean mandatory) {

        this.documentName = documentName;
        this.description = description;
        this.mandatory = mandatory;
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
}