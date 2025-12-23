package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-assignments")
@Tag(name = "Room Assignments")
public class RoomAssignmentController {

    private final RoomAssignmentService service;

    public RoomAssignmentController(RoomAssignmentService service) {
        this.service = service;
    }

    @PostMapping
    public RoomAssignmentRecord assignRoom(
            @RequestBody RoomAssignmentRecord assignment) {
        return service.assignRoom(assignment);
    }

    @PutMapping("/{id}/status")
    public RoomAssignmentRecord updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return service.updateStatus(id, status);
    }

    @GetMapping("/student/{studentId}")
    public List<RoomAssignmentRecord> getByStudent(
            @PathVariable Long studentId) {
        return service.getAssignmentsByStudent(studentId);
    }

    @GetMapping("/{id}")
    public RoomAssignmentRecord getById(@PathVariable Long id) {
        return service.getAssignmentById(id);
    }

    @GetMapping
    public List<RoomAssignmentRecord> getAll() {
        return service.getAllAssignments();
    }
}
