package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;


@Tag(name = "Student API", description = "Operations for student profiles")
@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentProfileService ser;

public StudentProfileController(StudentProfileService ser){
    this.ser = ser;
}


    @PostMapping
    public StudentProfile addStudent(@RequestBody StudentProfile profile) {
        return ser.createStudent(profile);
    }

    @GetMapping("/{id}")
    public StudentProfile getStudentById(@PathVariable Long id) {
        return ser.getStudentById(id);
    }

    @GetMapping
    public List<StudentProfile> getAllStudents() {
        return ser.getAllStudents();
    }

    @PutMapping("/{id}/status")
public StudentProfile updateStudentStatus(
        @PathVariable Long id,
        @RequestParam boolean active) {
    return ser.updateStudentStatus(id, active);
}


    @GetMapping("/lookup/{studentId}")
    public StudentProfile getByStudentId(@PathVariable String studentId) {
        return ser.findByStudentId(studentId);
    }
}
