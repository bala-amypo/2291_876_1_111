package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitProfileDto {
    private Long id;
    private Long studentId;
    private String sleepSchedule;
    private Integer studyHoursPerDay;
    private String cleanlinessLevel;
    private String noiseTolerance;
    private String socialPreference;
}