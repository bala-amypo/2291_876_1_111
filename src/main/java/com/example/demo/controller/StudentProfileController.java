package com.example.demo.controller;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profiles", description = "Student profile management")
public class StudentProfileController {
    private final StudentProfileService service;
    
    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<StudentProfile> createStudent(@RequestBody StudentProfileDto dto) {
        return ResponseEntity.ok(service.createStudent(dto));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStudentById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<StudentProfile> updateStatus(@PathVariable Long id, 
                                                        @RequestParam boolean active) {
        return ResponseEntity.ok(service.updateStudentStatus(id, active));
    }
    
    @GetMapping("/lookup/{studentId}")
    public ResponseEntity<StudentProfile> lookupByStudentId(@PathVariable String studentId) {
        return ResponseEntity.ok(service.findByStudentId(studentId));
    }
}