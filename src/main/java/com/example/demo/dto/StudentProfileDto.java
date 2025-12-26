package com.example.demo.dto;
import lombok.*;

@Data
@AllArgsConstructor
public class StudentProfileDto {
    private String studentId;
    private String email;
    private String fullName;
    private Boolean active;

    // getters and setters
}
