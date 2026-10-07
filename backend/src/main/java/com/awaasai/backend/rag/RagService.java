package com.awaasai.backend.rag;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RagService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public RagService(ObjectMapper objectMapper) {
        this.restTemplate = new RestTemplate();
        this.objectMapper = objectMapper;
    }

    public RagQueryResponse query(String question) {

        String jsonBody =
                "{\"question\":\"" + question.replace("\"", "\\\"") + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<>(jsonBody, headers);

        String response = restTemplate.postForObject(
                "http://localhost:8000/api/rag/query",
                request,
                String.class
        );

        try {
            Map<String, Object> data =
                    objectMapper.readValue(response, Map.class);

            return new RagQueryResponse(
                    (String) data.get("question"),
                    (String) data.get("answer"),
                    (java.util.List<Map<String, Object>>) data.get("sources")
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to parse RAG service response", e);
        }
    }
}