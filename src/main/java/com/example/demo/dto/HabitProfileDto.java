package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitProfileDto {
    private Long id;
    private Long studentId;
    private Boolean smoking;
    private Boolean drinking;
    private LocalTime sleepTime;
    private LocalTime wakeTime;
    private String cleanlinessLevel;
    private String noisePreference;
    private String studyStyle;
    private String socialPreference;
    private String visitorsFrequency;
    private String sleepSchedule;
    private Integer studyHoursPerDay;
    private String noiseTolerance;
}