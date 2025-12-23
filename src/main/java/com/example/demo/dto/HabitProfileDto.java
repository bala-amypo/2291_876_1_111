package com.example.demo.dto;

import com.example.demo.model.HabitProfile;
import lombok.Data;

@Data
public class HabitProfileDto {

    private Long studentId;

    private HabitProfile.SleepSchedule sleepSchedule;

    private Integer studyHoursPerDay;

    // BOTH use the same enum: Level
    private HabitProfile.Level cleanlinessLevel;

    private HabitProfile.Level noiseTolerance;

    private HabitProfile.SocialPreference socialPreference;
}
