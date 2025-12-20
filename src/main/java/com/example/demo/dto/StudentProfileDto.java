package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfileDto {
    private Long id;
    private String studentId;
    private String fullName;
    private String email;
    private String department;
    private Integer yearLevel;
    private Boolean active;
}