package com.example.demo.service;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.model.StudentProfile;

import java.util.List;

public interface StudentProfileService {

    // Create using entity directly
    StudentProfile createStudent(StudentProfile profile);

    // Create using DTO + userId
    StudentProfile createProfile(StudentProfileDto dto, Long userId);

    // Update profile using DTO
    StudentProfile updateProfile(Long id, StudentProfileDto dto);

    // Get profile (alias of getStudentById)
    StudentProfile getProfile(Long id);

    // Get student by DB primary key
    StudentProfile getStudentById(Long id);

    // Get all student profiles
    List<StudentProfile> getAllProfiles();

    // Same as above (kept because controller/interface expects it)
    List<StudentProfile> getAllStudents();

    // Find by business studentId (STRING)
    StudentProfile findByStudentId(String studentId);

    // Activate / deactivate student
    StudentProfile updateStudentStatus(Long id, boolean active);
}
