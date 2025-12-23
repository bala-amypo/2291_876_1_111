package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "habit_profile")
@Data
public class HabitProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "sleep_schedule")
    private SleepSchedule sleepSchedule;

    @Column(name = "study_hours_per_day")
    private Integer studyHoursPerDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "cleanliness_level")
    private CleanlinessLevel cleanlinessLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "noise_tolerance")
    private NoiseTolerance noiseTolerance;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_preference")
    private SocialPreference socialPreference;

    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum SleepSchedule {
        EARLY, REGULAR, LATE
    }

    public enum CleanlinessLevel {
        LOW, MEDIUM, HIGH
    }

    public enum NoiseTolerance {
        LOW, MEDIUM, HIGH
    }

    public enum SocialPreference {
        INTROVERT, BALANCED, EXTROVERT
    }
}
