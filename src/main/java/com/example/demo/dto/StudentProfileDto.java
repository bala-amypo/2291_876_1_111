package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileDto {
    private Long id;
    private String studentId;
    private String fullName;
    private String email;
    private Integer age;
    private String course;
    private Integer yearOfStudy;
    private String gender;
    private String roomTypePreference;
    private LocalTime sleepTime;
    private LocalTime wakeTime;
    private Boolean smoking;
    private Boolean drinking;
    private String noiseTolerance;
    private String studyTime;
    private String department;
    private Integer yearLevel;
    private Boolean active;
}