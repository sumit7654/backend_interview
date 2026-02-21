package com.aiinterview.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aiinterview.backend.model.InterviewQuestion;
import com.aiinterview.backend.service.InterviewQuestionService;

@RestController
@RequestMapping("/api/questions")
public class InterviewQuestionController {

    @Autowired
    private InterviewQuestionService questionService;

    // ADD QUESTION
    @PostMapping("/add")
    public InterviewQuestion addQuestion(@RequestBody InterviewQuestion question) {
        return questionService.saveQuestion(question);
    }

    // GET QUESTIONS BY INTERVIEW
    @GetMapping("/interview/{interviewId}")
    public List<InterviewQuestion> getQuestionsByInterview(
            @PathVariable Long interviewId) {
        return questionService.getQuestionsByInterview(interviewId);
    }

    // 🤖 SUBMIT ANSWER + AI EVALUATION
    @PostMapping("/answer")
    public InterviewQuestion submitAnswer(
            @RequestParam Long questionId,
            @RequestParam String jobRole,
            @RequestParam String answer) {

        return questionService.submitAnswerAI(
                questionId,
                jobRole,
                answer
        );
    }
}
