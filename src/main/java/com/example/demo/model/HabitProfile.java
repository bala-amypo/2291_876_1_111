package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "habit_profile")
public class HabitProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentId;

    private SleepSchedule sleepSchedule;

    private Integer studyHoursPerDay;

    @Enumerated(EnumType.STRING)
    private Level cleanlinessLevel;

    @Enumerated(EnumType.STRING)
    private Level noiseTolerance;

    @Enumerated(EnumType.STRING)
    private SocialPreference socialPreference;

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void validateAndUpdate() {
        if (studyHoursPerDay != null && studyHoursPerDay < 0) {
            throw new IllegalArgumentException("study hours must be >= 0");
        }
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public SleepSchedule getSleepSchedule() {
        return sleepSchedule;
    }

    public void setSleepSchedule(SleepSchedule sleepSchedule) {
        this.sleepSchedule = sleepSchedule;
    }

    public Integer getStudyHoursPerDay() {
        return studyHoursPerDay;
    }

    public void setStudyHoursPerDay(Integer studyHoursPerDay) {
        this.studyHoursPerDay = studyHoursPerDay;
    }

    public Level getCleanlinessLevel() {
        return cleanlinessLevel;
    }

    public void setCleanlinessLevel(Level cleanlinessLevel) {
        this.cleanlinessLevel = cleanlinessLevel;
    }

    public Level getNoiseTolerance() {
        return noiseTolerance;
    }

    public void setNoiseTolerance(Level noiseTolerance) {
        this.noiseTolerance = noiseTolerance;
    }

    public SocialPreference getSocialPreference() {
        return socialPreference;
    }

    public void setSocialPreference(SocialPreference socialPreference) {
        this.socialPreference = socialPreference;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public HabitProfile(Long id, Long studentId, SleepSchedule sleepSchedule, Integer studyHoursPerDay,
            Level cleanlinessLevel, Level noiseTolerance, SocialPreference socialPreference, LocalDateTime updatedAt) {
        this.id = id;
        this.studentId = studentId;
        this.sleepSchedule = sleepSchedule;
        this.studyHoursPerDay = studyHoursPerDay;
        this.cleanlinessLevel = cleanlinessLevel;
        this.noiseTolerance = noiseTolerance;
        this.socialPreference = socialPreference;
        this.updatedAt = updatedAt;
    }

    public HabitProfile() {
    }
    

}