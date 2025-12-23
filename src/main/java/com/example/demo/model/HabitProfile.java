package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "habit_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitProfile {

    // ===== ENUMS (TEST REQUIRES THESE EXACT NAMES) =====
    public enum CleanlinessLevel { LOW, MEDIUM, HIGH }
    public enum NoiseTolerance { LOW, BALANCED, HIGH }
    public enum SleepSchedule { EARLY, NIGHT, FLEXIBLE }
    public enum SocialPreference { INTROVERT, AMBIVERT, EXTROVERT }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔴 TEST USES THIS (NOT StudentProfile)
    private Long studentId;

    private Boolean smoking;
    private Boolean drinking;

    private LocalTime sleepTime;
    private LocalTime wakeTime;

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
