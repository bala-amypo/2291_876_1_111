package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.time.LocalDateTime;


@Entity
@Table(name = "habit_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitProfile {
public enum CleanlinessLevel {
    LOW, MEDIUM, HIGH
}

public enum NoiseTolerance {
    LOW, MEDIUM, HIGH
}

public enum SleepSchedule {
    EARLY, REGULAR, LATE
}

public enum SocialPreference {
    INTROVERT, EXTROVERT, BALANCED
}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private Boolean smoking;
    private Boolean drinking;

    private LocalTime sleepTime;
    private LocalTime wakeTime;

private LocalDateTime createdAt;
private LocalDateTime updatedAt;

@PrePersist
void onCreate() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
}

@PreUpdate
void onUpdate() {
    updatedAt = LocalDateTime.now();
}

    @Enumerated(EnumType.STRING)
    private CleanlinessLevel cleanlinessLevel;

    @Enumerated(EnumType.STRING)
    private NoiseTolerance noiseTolerance;

    @Enumerated(EnumType.STRING)
    private SleepSchedule sleepSchedule;

    @Enumerated(EnumType.STRING)
    private SocialPreference socialPreference;

    private Integer studyHoursPerDay;
}
