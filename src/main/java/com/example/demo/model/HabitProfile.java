package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "habit_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long studentId;
    
    @Enumerated(EnumType.STRING)
    private SleepSchedule sleepSchedule;
    
    private Integer studyHoursPerDay;
    
    @Enumerated(EnumType.STRING)
    private CleanlinessLevel cleanlinessLevel;
    
    @Enumerated(EnumType.STRING)
    private NoiseTolerance noiseTolerance;
    
    @Enumerated(EnumType.STRING)
    private SocialPreference socialPreference;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

// Enums
enum SleepSchedule {
    EARLY, REGULAR, LATE
}

enum CleanlinessLevel {
    LOW, MEDIUM, HIGH
}

enum NoiseTolerance {
    LOW, MEDIUM, HIGH
}

enum SocialPreference {
    INTROVERT, BALANCED, EXTROVERT
}