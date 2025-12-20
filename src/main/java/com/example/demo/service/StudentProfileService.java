package com.example.demo.service;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.model.StudentProfile;
import java.util.List;

public interface StudentProfileService {
    StudentProfile createStudent(StudentProfileDto dto);
    StudentProfile getStudentById(Long id);
    List<StudentProfile> getAllStudents();
    StudentProfile findByStudentId(String studentId);
    StudentProfile updateStudentStatus(Long id, boolean active);
}