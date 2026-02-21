package com.aiinterview.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiinterview.backend.model.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    // Get all interviews of a specific user
    List<Interview> findByUserId(Long userId);
}
