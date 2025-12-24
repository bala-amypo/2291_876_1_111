package com.example.demo.controller;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profiles", description = "Student profile management")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @PostMapping
    @Operation(summary = "Create student profile")
    public ResponseEntity<StudentProfile> create(@RequestBody StudentProfileDto dto, 
                                                        @RequestParam Long userId) {
        return ResponseEntity.ok(studentProfileService.createProfile(dto, userId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update student profile")
    public ResponseEntity<StudentProfile> updateProfile(@PathVariable Long id, 
                                                        @RequestBody StudentProfileDto dto) {
        return ResponseEntity.ok(studentProfileService.updateProfile(id, dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID")
    public ResponseEntity<StudentProfile> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(studentProfileService.getProfile(id));
    }

    @GetMapping
    @Operation(summary = "Get all students")
    public ResponseEntity<List<StudentProfile>> getAllProfiles() {
        return ResponseEntity.ok(studentProfileService.getAllProfiles());
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update student status")
    public ResponseEntity<StudentProfile> updateStatus(@PathVariable Long id, 
                                                       @RequestParam boolean active) {
        return ResponseEntity.ok(studentProfileService.updateStudentStatus(id, active));
    }

    @GetMapping("/lookup/{studentId}")
    @Operation(summary = "Lookup student by student ID")
    public ResponseEntity<StudentProfile> lookupByStudentId(@PathVariable String studentId) {
        return ResponseEntity.ok(studentProfileService.findByStudentId(studentId));
    }
}