package com.example.demo.dto;

import com.example.demo.model.HabitProfile;

public class HabitProfileDto {

    private Long studentId;
    private Integer studyHoursPerDay;
    private HabitProfile.SleepSchedule sleepSchedule;
    private HabitProfile.CleanlinessLevel cleanlinessLevel;
    private HabitProfile.NoiseTolerance noiseTolerance;
    private HabitProfile.SocialPreference socialPreference;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getStudyHoursPerDay() {
        return studyHoursPerDay;
    }

    public void setStudyHoursPerDay(Integer studyHoursPerDay) {
        this.studyHoursPerDay = studyHoursPerDay;
    }

    public HabitProfile.SleepSchedule getSleepSchedule() {
        return sleepSchedule;
    }

    public void setSleepSchedule(HabitProfile.SleepSchedule sleepSchedule) {
        this.sleepSchedule = sleepSchedule;
    }

    public HabitProfile.CleanlinessLevel getCleanlinessLevel() {
        return cleanlinessLevel;
    }

    public void setCleanlinessLevel(HabitProfile.CleanlinessLevel cleanlinessLevel) {
        this.cleanlinessLevel = cleanlinessLevel;
    }

    public HabitProfile.NoiseTolerance getNoiseTolerance() {
        return noiseTolerance;
    }

    public void setNoiseTolerance(HabitProfile.NoiseTolerance noiseTolerance) {
        this.noiseTolerance = noiseTolerance;
    }

    public HabitProfile.SocialPreference getSocialPreference() {
        return socialPreference;
    }

    public void setSocialPreference(HabitProfile.SocialPreference socialPreference) {
        this.socialPreference = socialPreference;
    }
}
