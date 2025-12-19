package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;

@RestController
@RequestMapping("/students")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping
    public StudentProfile createStudent(@RequestBody StudentProfile student) {
        return service.createStudent(student);
    }
}
