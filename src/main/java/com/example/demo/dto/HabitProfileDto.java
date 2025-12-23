package com.example.demo.dto;

import com.example.demo.model.HabitProfile;
import lombok.Data;

@Data
public class HabitProfileDto {
    private Long studentId;
    private HabitProfile.SleepSchedule sleepSchedule;
    private Integer studyHoursPerDay;
    private HabitProfile.CleanlinessLevel cleanlinessLevel;
    private HabitProfile.NoiseTolerance noiseTolerance;
    private HabitProfile.SocialPreference socialPreference;
}
