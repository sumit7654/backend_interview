package com.aiinterview.backend.service;

import com.aiinterview.backend.dto.EvaluationResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AIAnswerEvaluatorService {

    @Autowired
    private GeminiAIService geminiAIService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EvaluationResult evaluate(
            String jobRole,
            String question,
            String answer) {

        String prompt = """
        You are a technical interviewer for a %s role.

        Question:
        %s

        Candidate Answer:
        %s

        Evaluate the answer and return ONLY valid JSON in this exact format:

        {
          "score": number between 0 and 10,
          "strengths": [ "point1", "point2" ],
          "improvements": [ "point1", "point2" ]
        }

        No explanation outside JSON.
        """.formatted(jobRole, question, answer);

        String response = geminiAIService.callGemini(prompt);

        return parseEvaluation(response);
    }

    // 🔥 THIS PART YOU WERE MISSING
    private EvaluationResult parseEvaluation(String response) {
        try {
            /*
             Gemini actual response structure:
             {
               "candidates": [
                 {
                   "content": {
                     "parts": [
                       { "text": "{ \"score\": 7, ... }" }
                     ]
                   }
                 }
               ]
             }
            */

            JsonNode root = objectMapper.readTree(response);

            String jsonText =
                    root.path("candidates")
                        .get(0)
                        .path("content")
                        .path("parts")
                        .get(0)
                        .path("text")
                        .asText();

            return objectMapper.readValue(jsonText, EvaluationResult.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Gemini evaluation response", e);
        }
    }
}
