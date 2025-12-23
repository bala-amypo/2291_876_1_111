package com.example.demo.controller;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profiles", description = "Student profile management")
@RequiredArgsConstructor
public class StudentProfileController {
    private final StudentProfileService studentProfileService;

    @PostMapping
    public ResponseEntity<StudentProfile> createStudent(@RequestBody StudentProfileDto dto) {
        StudentProfile profile = new StudentProfile();
        profile.setStudentId(dto.getStudentId());
        profile.setFullName(dto.getFullName());
        profile.setEmail(dto.getEmail());
        profile.setDepartment(dto.getDepartment());
        profile.setYearLevel(dto.getYearLevel());
        profile.setActive(dto.isActive());  // FIXED: Simple boolean assignment
        return ResponseEntity.ok(studentProfileService.createStudent(profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentProfileService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllStudents() {
        return ResponseEntity.ok(studentProfileService.getAllStudents());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<StudentProfile> updateStudentStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(studentProfileService.updateStudentStatus(id, active));
    }

    @GetMapping("/lookup/{studentId}")
    public ResponseEntity<StudentProfile> findByStudentId(@PathVariable String studentId) {
        return studentProfileService.findByStudentId(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
