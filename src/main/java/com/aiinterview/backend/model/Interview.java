package com.aiinterview.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "interviews") //Java class ↔ MySQL table mapping
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "job_role")
    private String jobRole;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // ---------- Getters ----------
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getJobRole() {
        return jobRole;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // ---------- Setters ----------
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
