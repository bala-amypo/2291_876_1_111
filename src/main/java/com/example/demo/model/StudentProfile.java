package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "student_profiles",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "studentId"),
        @UniqueConstraint(columnNames = "email")
    }
)
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    private String department;
    private Integer yearLevel;
    private Boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();

    // JPA requires this
    protected StudentProfile() {}

    // parameterised constructor
    public StudentProfile(String studentId, String fullName, String email,
                          String department, Integer yearLevel, Boolean active) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.yearLevel = yearLevel;
        this.active = active;
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public String getStudentId() { return studentId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public Boolean isActive() { return active; }
}
