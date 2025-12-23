package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "habit_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true, nullable = false)
    private StudentProfile student;

    @Column(nullable = false)
    private Boolean smoking = false;

    @Column(nullable = false)
    private Boolean drinking = false;

    private LocalTime sleepTime;

    private LocalTime wakeTime;

    @Column(nullable = false)
    private String cleanlinessLevel;

    @Column(nullable = false)
    private String noisePreference;

    @Column(nullable = false)
    private String studyStyle;

    @Column(nullable = false)
    private String socialPreference;

    private String visitorsFrequency;

    @Column(nullable = false)
    private String sleepSchedule;

    @Column(nullable = false)
    private Integer studyHoursPerDay = 0;

    @Column(nullable = false)
    private String noiseTolerance;

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
      public enum CleanlinessLevel { LOW, MEDIUM, HIGH }
    public enum NoiseTolerance { LOW, MEDIUM, HIGH }
    public enum SleepSchedule { EARLY_SLEEPER, NIGHT_OWL, FLEXIBLE }
    public enum SocialPreference { INTROVERT, AMBIVERT, EXTROVERT }
}   