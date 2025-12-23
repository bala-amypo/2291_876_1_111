package com.example.demo.service;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.model.StudentProfile;
import java.util.List;

public interface StudentProfileService {
    StudentProfile createStudent(StudentProfile profile);
    StudentProfile createProfile(StudentProfileDto dto, Long userId);
    StudentProfile updateProfile(Long id, StudentProfileDto dto);
    StudentProfile getProfile(Long id);
    StudentProfile getStudentById(Long id);
    List<StudentProfile> getAllProfiles();
    List<StudentProfile> getAllStudents();
    StudentProfile findByStudentId(String studentId);
    StudentProfile updateStudentStatus(Long id, boolean active);
}