package com.awaasai.backend.application;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/officer")
public class OfficerRecommendationController {

    private final OfficerRecommendationService recommendationService;

    public OfficerRecommendationController(
            OfficerRecommendationService recommendationService) {

        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommendations/{applicationId}")
    public OfficerRecommendationResponse getRecommendation(
            @PathVariable Long applicationId) {

        return recommendationService.getRecommendation(applicationId);
    }
}