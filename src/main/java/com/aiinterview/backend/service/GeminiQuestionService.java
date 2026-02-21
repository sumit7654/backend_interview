package com.aiinterview.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeminiQuestionService {

    @Autowired
    private GeminiAIService geminiAIService;

    public List<String> generateQuestions(String jobRole) {

        String prompt = """
        Generate 5 technical interview questions for a %s.
        Each question on a new line.
        Do not number them.
        """.formatted(jobRole);

        String response = geminiAIService.callGemini(prompt);

        return extractQuestions(response);
    }

    private List<String> extractQuestions(String response) {

        List<String> questions = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            String text =
                root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            // split by new line
            for (String q : text.split("\\n")) {
                if (!q.trim().isEmpty()) {
                    questions.add(q.trim());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Gemini response", e);
        }

        return questions;
    }
}
