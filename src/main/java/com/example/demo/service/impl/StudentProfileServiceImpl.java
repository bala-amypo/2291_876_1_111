package com.example.demo.service.impl;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StudentProfile;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository repo;

    public StudentProfileServiceImpl(StudentProfileRepository repo) {
        this.repo = repo;
    }

    public StudentProfile createStudent(StudentProfile s) {
        if (repo.findByStudentId(s.getStudentId()).isPresent())
            throw new IllegalArgumentException("studentId exists");

        if (repo.findByEmail(s.getEmail()).isPresent())
            throw new IllegalArgumentException("email exists");

        return repo.save(s);
    }

    public StudentProfile getStudentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public StudentProfile updateStudentStatus(Long id, boolean active) {
        StudentProfile s = getStudentById(id);
        s.setActive(active);
        return repo.save(s);
    }

    public List<StudentProfile> getAllStudents() {
        return repo.findAll();
    }

    public Optional<StudentProfile> findByStudentId(String sid) {
        return repo.findByStudentId(sid);
    }
}
