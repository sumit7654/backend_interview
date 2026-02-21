package com.aiinterview.backend.model;

import jakarta.persistence.*;

@Entity //Ye class database table ko represent karti hai
@Table(name = "users") //Direct mapping
public class User {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL ka AUTO_INCREMENT
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true) //Java field → MySQL column
    private String email;

    @Column(name = "password")
    private String password;

    // ---------- Getters ----------
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // ---------- Setters ----------
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
