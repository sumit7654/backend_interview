package com.aiinterview.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aiinterview.backend.model.InterviewQuestion;
import com.aiinterview.backend.service.InterviewQuestionService;

@RestController
@RequestMapping("/api/ai")
public class AIQuestionController {

    @Autowired
    private InterviewQuestionService questionService;

    // GENERATE AI QUESTIONS AND SAVE
    @PostMapping("/generate")
    public List<InterviewQuestion> generateQuestions(
            @RequestParam Long interviewId,
            @RequestParam String jobRole) {

        return questionService.generateAndSaveQuestions(interviewId, jobRole);
    }

}
