package com.aiinterview.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.aiinterview.backend.model.Interview;
import com.aiinterview.backend.service.InterviewService;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    // CREATE INTERVIEW API
    @PostMapping("/create")
    public Interview createInterview(
            @RequestParam Long userId,
            @RequestParam String jobRole) {

        return interviewService.createInterview(userId, jobRole);
    }
    
    // GET INTERVIEWS BY USER ID
    @GetMapping("/user/{userId}")
    public List<Interview> getUserInterviews(@PathVariable Long userId) {
    return interviewService.getInterviewsByUser(userId);
}

// MARK INTERVIEW AS COMPLETED
    @PutMapping("/{interviewId}/complete")
    public Interview completeInterview(@PathVariable Long interviewId) {
    return interviewService.completeInterview(interviewId);
}

}
