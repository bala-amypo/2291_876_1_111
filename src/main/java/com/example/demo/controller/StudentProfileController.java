package com.example.demo.controller;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.dto.StudentStatusUpdateDto;
import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<StudentProfile> createStudent(
            @RequestBody StudentProfileDto dto) {

        StudentProfile profile = new StudentProfile(
                dto.getStudentId(),
                dto.getFullName(),
                dto.getEmail(),
                dto.getDepartment(),
                dto.getYearLevel()
        );

        StudentProfile created = studentProfileService.createStudent(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StudentProfile> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(
                studentProfileService.getStudentById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllStudents() {
        return ResponseEntity.ok(
                studentProfileService.getAllStudents()
        );
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<StudentProfile> updateStudentStatus(
            @PathVariable Long id,
            @RequestBody StudentStatusUpdateDto dto) {

        StudentProfile updated =
                studentProfileService.updateStudentStatus(id, dto.isActive());

        return ResponseEntity.ok(updated);
    }

    @GetMapping("/lookup/{studentId}")
    public ResponseEntity<StudentProfile> getByStudentId(
            @PathVariable String studentId) {

        return ResponseEntity.ok(
                studentProfileService.getStudentByStudentId(studentId)
        );
    }
}
