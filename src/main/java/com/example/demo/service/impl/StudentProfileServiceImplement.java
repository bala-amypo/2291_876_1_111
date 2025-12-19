package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.StudentAlreadyExistsException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;

@Service
public class StudentProfileServiceImplement implements StudentProfileService {

    private final StudentProfileRepository repository;

    public StudentProfileServiceImplement(StudentProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentProfile createStudent(StudentProfile student) {

        if (repository.existsByStudentId(student.getStudentId())) {
            throw new StudentAlreadyExistsException("studentId exists");
        }

        if (repository.existsByEmail(student.getEmail())) {
            throw new StudentAlreadyExistsException("email exists");
        }

        return repository.save(student);
    }
}
