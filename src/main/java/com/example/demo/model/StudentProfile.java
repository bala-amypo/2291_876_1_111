package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;
    private String email;
    private String fullName;
    private Boolean active = true;

    // getters and setters
}
