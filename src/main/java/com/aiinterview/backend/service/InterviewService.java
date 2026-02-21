package com.aiinterview.backend.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiinterview.backend.model.Interview;
import com.aiinterview.backend.repository.InterviewRepository;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    // CREATE INTERVIEW
    public Interview createInterview(Long userId, String jobRole) {

        Interview interview = new Interview();
        interview.setUserId(userId);
        interview.setJobRole(jobRole);
        interview.setStatus("CREATED");
        interview.setCreatedAt(LocalDateTime.now());

        return interviewRepository.save(interview);
    }
    
    // GET ALL INTERVIEWS OF A USER
    public List<Interview> getInterviewsByUser(Long userId) {
    return interviewRepository.findByUserId(userId);
    }
    
    // MARK INTERVIEW AS COMPLETED
    public Interview completeInterview(Long interviewId) {

    Interview interview = interviewRepository.findById(interviewId)
            .orElseThrow(() -> new RuntimeException("Interview not found"));

    interview.setStatus("COMPLETED");

    return interviewRepository.save(interview);
    }


}
