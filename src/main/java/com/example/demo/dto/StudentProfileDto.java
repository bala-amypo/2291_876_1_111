package com.example.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentProfileDto {
    private String studentId;
    private String email;
    private String fullName;
    private Boolean active;

}
