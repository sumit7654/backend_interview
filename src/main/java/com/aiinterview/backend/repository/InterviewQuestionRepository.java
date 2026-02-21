package com.aiinterview.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiinterview.backend.model.InterviewQuestion;

public interface InterviewQuestionRepository
        extends JpaRepository<InterviewQuestion, Long> {

    // Get all questions of a specific interview
    List<InterviewQuestion> findByInterviewId(Long interviewId);
}
