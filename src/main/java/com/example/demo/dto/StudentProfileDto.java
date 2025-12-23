package com.example.demo.dto;

import lombok.Data;

@Data
public class StudentProfileDto {
    private String studentId;
    private String fullName;
    private String email;
    private String department;
    private Integer yearLevel;
    private boolean active;
}
