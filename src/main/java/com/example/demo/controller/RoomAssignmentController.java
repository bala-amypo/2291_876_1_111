package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-assignments")
@Tag(name = "Room Assignments", description = "Room assignment management")
public class RoomAssignmentController {
    private final RoomAssignmentService service;
    
    public RoomAssignmentController(RoomAssignmentService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<RoomAssignmentRecord> assignRoom(@RequestBody RoomAssignmentRecord assignment) {
        return ResponseEntity.ok(service.assignRoom(assignment));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<RoomAssignmentRecord> updateStatus(@PathVariable Long id,
                                                              @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<RoomAssignmentRecord>> getAssignmentsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getAssignmentsByStudent(studentId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RoomAssignmentRecord> getAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAssignmentById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<RoomAssignmentRecord>> getAllAssignments() {
        return ResponseEntity.ok(service.getAllAssignments());
    }
}