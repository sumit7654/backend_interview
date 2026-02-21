package com.aiinterview.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiinterview.backend.dto.EvaluationResult;
import com.aiinterview.backend.model.InterviewQuestion;
import com.aiinterview.backend.repository.InterviewQuestionRepository;

@Service
public class InterviewQuestionService {

    @Autowired
    private InterviewQuestionRepository questionRepository;

    @Autowired
    private GeminiQuestionService geminiQuestionService;

    @Autowired
    private AIAnswerEvaluatorService aiAnswerEvaluatorService;

    // SAVE SINGLE QUESTION
    public InterviewQuestion saveQuestion(InterviewQuestion question) {
        return questionRepository.save(question);
    }

    // GET QUESTIONS OF INTERVIEW
    public List<InterviewQuestion> getQuestionsByInterview(Long interviewId) {
        return questionRepository.findByInterviewId(interviewId);
    }

    // 🤖 GENERATE QUESTIONS (AI) + SAVE
    public List<InterviewQuestion> generateAndSaveQuestions(
            Long interviewId, String jobRole) {

        List<String> questions = geminiQuestionService.generateQuestions(jobRole);
        List<InterviewQuestion> saved = new ArrayList<>();

        for (String q : questions) {
            InterviewQuestion iq = new InterviewQuestion();
            iq.setInterviewId(interviewId);
            iq.setQuestion(q);
            iq.setDifficulty("Medium");
            iq.setScore(0);
            iq.setStrengths(new ArrayList<>());
            iq.setImprovements(new ArrayList<>());

            saved.add(questionRepository.save(iq));
        }

        return saved;
    }

    // 🤖 SUBMIT ANSWER + GEMINI EVALUATION
    public InterviewQuestion submitAnswerAI(
            Long questionId,
            String jobRole,
            String answer) {

        InterviewQuestion question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        question.setAnswer(answer);

        // 🔥 GEMINI EVALUATION
        EvaluationResult result = aiAnswerEvaluatorService.evaluate(
                jobRole,
                question.getQuestion(),
                answer
        );

        question.setScore(result.getScore());
        question.setStrengths(result.getStrengths());       // ✅ LIST
        question.setImprovements(result.getImprovements()); // ✅ LIST

        return questionRepository.save(question);
    }
}
