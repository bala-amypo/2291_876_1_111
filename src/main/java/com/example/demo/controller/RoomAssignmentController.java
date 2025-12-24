package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-assignments")
@Tag(name = "Room Assignments", description = "Room assignment management")
public class RoomAssignmentController {

    private final RoomAssignmentService roomAssignmentService;

    public RoomAssignmentController(RoomAssignmentService roomAssignmentService) {
        this.roomAssignmentService = roomAssignmentService;
    }

    @PostMapping
    @Operation(summary = "Assign room")
    public ResponseEntity<RoomAssignmentRecord> assignRoom(
            @RequestBody RoomAssignmentRecord assignment
    ) {
        return ResponseEntity.ok(
                roomAssignmentService.assignRoom(assignment)
        );
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update room assignment status")
    public ResponseEntity<RoomAssignmentRecord> updateStatus(
        @PathVariable Long id,
        @RequestBody String status
    ){
        return ResponseEntity.ok(
                roomAssignmentService.updateStatus(id, status)
        );
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get all assignments for a student")
    public ResponseEntity<List<RoomAssignmentRecord>> getAssignmentsByStudent(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(
                roomAssignmentService.getAssignmentsByStudent(studentId)
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get assignment by ID")
    public ResponseEntity<RoomAssignmentRecord> getAssignment(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                roomAssignmentService.getAssignmentById(id)
        );
    }

    @GetMapping
    @Operation(summary = "Get all room assignments")
    public ResponseEntity<List<RoomAssignmentRecord>> getAllAssignments() {
        return ResponseEntity.ok(
                roomAssignmentService.getAllAssignments()
        );
    }

    @PostMapping("/assign")
    public RoomAssignmentRecord assign(@RequestBody RoomAssignmentRecord record) {
        return roomAssignmentService.assignRoom(record);
    }

}
