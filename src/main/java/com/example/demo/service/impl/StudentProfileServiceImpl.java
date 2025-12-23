package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {
    
    private final StudentProfileRepository studentProfileRepository;

    // EXACT TEST CONSTRUCTOR - NO LOMBOK
    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public StudentProfile createStudent(StudentProfile profile) {
        Optional<StudentProfile> existing = studentProfileRepository.findByStudentId(profile.getStudentId());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("studentId exists");
        }
        return studentProfileRepository.save(profile);
    }

    @Override
    public StudentProfile getStudentById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    @Override
    public Optional<StudentProfile> findByStudentId(String studentId) {
        return studentProfileRepository.findByStudentId(studentId);
    }

    @Override
    public StudentProfile updateStudentStatus(Long id, boolean active) {
        StudentProfile student = getStudentById(id);
        student.setActive(active);
        return studentProfileRepository.save(student);
    }
}
