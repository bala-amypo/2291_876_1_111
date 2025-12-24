package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HabitProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Integer studyHoursPerDay;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private SleepSchedule sleepSchedule;

    @Enumerated(EnumType.STRING)
    private CleanlinessLevel cleanlinessLevel;

    @Enumerated(EnumType.STRING)
    private NoiseTolerance noiseTolerance;

    @Enumerated(EnumType.STRING)
    private SocialPreference socialPreference;

    public enum SleepSchedule { EARLY, REGULAR, LATE }
    public enum CleanlinessLevel { LOW, MEDIUM, HIGH }
    public enum NoiseTolerance { LOW, MEDIUM, HIGH }
    public enum SocialPreference { INTROVERT, BALANCED, EXTROVERT }

    // getters and setters (or Lombok @Data if enabled)
}
