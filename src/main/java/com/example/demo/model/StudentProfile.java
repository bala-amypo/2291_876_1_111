package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "student_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_account_id", unique = true, nullable = false)
    private UserAccount userAccount;

    @Column(unique = true, nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String course;

    @Column(nullable = false)
    private Integer yearOfStudy;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String roomTypePreference;

    private LocalTime sleepTime;

    private LocalTime wakeTime;

    private Boolean smoking = false;

    private Boolean drinking = false;

    private String noiseTolerance;

    private String studyTime;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private Integer yearLevel;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}       