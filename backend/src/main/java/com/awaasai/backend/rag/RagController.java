package com.awaasai.backend.rag;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/query")
    public RagQueryResponse query(@RequestBody RagQueryRequest request) {

        return ragService.query(request.getQuestion());
    }
}