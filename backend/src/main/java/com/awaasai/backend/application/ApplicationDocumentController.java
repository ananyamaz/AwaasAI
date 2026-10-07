package com.awaasai.backend.application;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationDocumentController {

    private final ApplicationDocumentService service;

    public ApplicationDocumentController(
            ApplicationDocumentService service) {
        this.service = service;
    }

    @GetMapping("/{id}/missing-documents")
    public List<MissingDocumentResponse> getMissingDocuments(
        @PathVariable Long id) {

    return service.getMissingDocuments(id);
}
}