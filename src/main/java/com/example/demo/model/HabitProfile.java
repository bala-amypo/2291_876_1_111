package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "habit_profiles")
public class HabitProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    @Enumerated(EnumType.STRING)
    private SleepSchedule sleepSchedule;

    private Integer studyHoursPerDay;

    @Enumerated(EnumType.STRING)
    private Level cleanlinessLevel;

    @Enumerated(EnumType.STRING)
    private Level noiseTolerance;

    @Enumerated(EnumType.STRING)
    private SocialPreference socialPreference;

    private LocalDateTime updatedAt = LocalDateTime.now();

    protected HabitProfile() {}

    public HabitProfile(Long studentId, SleepSchedule sleepSchedule,
                        Integer studyHoursPerDay, Level cleanlinessLevel,
                        Level noiseTolerance, SocialPreference socialPreference) {
        this.studentId = studentId;
        this.sleepSchedule = sleepSchedule;
        this.studyHoursPerDay = studyHoursPerDay;
        this.cleanlinessLevel = cleanlinessLevel;
        this.noiseTolerance = noiseTolerance;
        this.socialPreference = socialPreference;
        this.updatedAt = LocalDateTime.now();
    }

    public enum SleepSchedule { EARLY, REGULAR, LATE }
    public enum Level { LOW, MEDIUM, HIGH }
    public enum SocialPreference { INTROVERT, BALANCED, EXTROVERT }
}
