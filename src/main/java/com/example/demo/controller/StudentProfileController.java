package com.example.demo.controller;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profiles", description = "Student profile management")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @PostMapping
    public ResponseEntity<StudentProfile> createStudent(@RequestBody StudentProfileDto dto) {
        StudentProfile profile = new StudentProfile(dto.getStudentId(), dto.getFullName(), 
                dto.getEmail(), dto.getDepartment(), dto.getYearLevel());
        StudentProfile created = studentProfileService.createStudent(profile);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getStudent(@PathVariable Long id) {
        StudentProfile profile = studentProfileService.getStudentById(id);
        return ResponseEntity.ok(profile);
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllStudents() {
        List<StudentProfile> profiles = studentProfileService.getAllStudents();
        return ResponseEntity.ok(profiles);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<StudentProfile> updateStudentStatus(@PathVariable Long id, 
                                                            @RequestBody Map<String, Boolean> request) {
        boolean active = request.get("active");
        StudentProfile updated = studentProfileService.updateStudentStatus(id, active);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/lookup/{studentId}")
    public ResponseEntity<StudentProfile> lookupStudent(@PathVariable String studentId) {
        StudentProfile profile = studentProfileService.getStudentByStudentId(studentId);
        return ResponseEntity.ok(profile);
    }
}