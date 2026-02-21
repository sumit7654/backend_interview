package com.aiinterview.backend.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeminiAIService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String GEMINI_URL =
        "https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String callGemini(String prompt) {
        try {
            // Build JSON safely
            Map<String, Object> textPart = Map.of("text", prompt);
            Map<String, Object> content = Map.of("parts", List.of(textPart));
            Map<String, Object> body = Map.of("contents", List.of(content));

            String json = objectMapper.writeValueAsString(body);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-goog-api-key", apiKey);

            HttpEntity<String> entity = new HttpEntity<>(json, headers);

            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.postForObject(GEMINI_URL, entity, String.class);

        } catch (Exception e) {
            throw new RuntimeException("Gemini API call failed", e);
        }
    }
}
