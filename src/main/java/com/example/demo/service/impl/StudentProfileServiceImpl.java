package com.example.demo.service.impl;

import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.StudentProfileService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentRepo;
    private final UserAccountRepository userRepo;

    public StudentProfileServiceImpl(StudentProfileRepository studentRepo,
                                     UserAccountRepository userRepo) {
        this.studentRepo = studentRepo;
        this.userRepo = userRepo;
    }

    @Override
    public StudentProfile createStudent(StudentProfile profile) {
        return studentRepo.save(profile);
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public StudentProfile getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    // ✅ REQUIRED BY INTERFACE (String, NOT Long)
    @Override
    public StudentProfile findByStudentId(String studentId) {
        return studentRepo.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public List<StudentProfile> getAllProfiles() {
        return studentRepo.findAll();
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public StudentProfile updateStudentStatus(Long id, boolean active) {
        StudentProfile profile = getStudentById(id);
        profile.setActive(active);
        return studentRepo.save(profile);
    }
}
