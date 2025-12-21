package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/room-assignments")
public class RoomAssignmentController {

    private final RoomAssignmentService service;

    public RoomAssignmentController(RoomAssignmentService service) {
        this.service = service;
    }

    @PostMapping
    public RoomAssignmentRecord assign(@RequestBody RoomAssignmentRecord r) {
        return service.assignRoom(r);
    }

    @PutMapping("/{id}/status")
    public RoomAssignmentRecord update(@PathVariable Long id, @RequestParam String status) {
        return service.updateRoomStatus(id, status);
    }

    @GetMapping("/student/{studentId}")
    public List<RoomAssignmentRecord> byStudent(@PathVariable Long studentId) {
        return service.getAssignmentsForStudent(studentId);
    }

    @GetMapping("/{id}")
    public RoomAssignmentRecord byId(@PathVariable Long id) {
        return service.getAssignmentById(id);
    }

    @GetMapping
    public List<RoomAssignmentRecord> getAll() {
        return service.getAllRoomAssignments();
    }
}