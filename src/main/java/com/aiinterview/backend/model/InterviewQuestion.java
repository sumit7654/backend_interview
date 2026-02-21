package com.aiinterview.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "interview_questions")
public class InterviewQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "interview_id", nullable = false)
    private Long interviewId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String question;

    @Column(columnDefinition = "TEXT")
    private String answer;

    private String difficulty;

    private Integer score;

    // 🔥 Gemini strengths list
        @ElementCollection
    @CollectionTable(
        name = "question_strengths",
        joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "strength", columnDefinition = "TEXT")
    private List<String> strengths;

    // 🔥 Gemini improvements list
    @ElementCollection
    @CollectionTable(
        name = "question_improvements",
        joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "improvement", columnDefinition = "TEXT")
    private List<String> improvements;

    // ---------- Getters ----------
    public Long getId() {
        return id;
    }

    public Long getInterviewId() {
        return interviewId;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Integer getScore() {
        return score;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public List<String> getImprovements() {
        return improvements;
    }

    // ---------- Setters ----------
    public void setId(Long id) {
        this.id = id;
    }

    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public void setImprovements(List<String> improvements) {
        this.improvements = improvements;
    }
}
