package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "room_assignments")
public class RoomAssignmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private Long studentAId;
    private Long studentBId;

    private LocalDateTime assignedAt;

    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;

    // ✅ No-args
    public RoomAssignmentRecord() {
    }

    // ✅ All-args
    public RoomAssignmentRecord(
            Long id,
            String roomNumber,
            Long studentAId,
            Long studentBId,
            LocalDateTime assignedAt,
            AssignmentStatus status
    ) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.studentAId = studentAId;
        this.studentBId = studentBId;
        this.assignedAt = assignedAt;
        this.status = status;
    }

    public enum AssignmentStatus {
        ACTIVE, COMPLETE, CANCELLED
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public Long getStudentAId() { return studentAId; }
    public void setStudentAId(Long studentAId) { this.studentAId = studentAId; }

    public Long getStudentBId() { return studentBId; }
    public void setStudentBId(Long studentBId) { this.studentBId = studentBId; }

    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }

    public AssignmentStatus getStatus() { return status; }
    public void setStatus(AssignmentStatus status) { this.status = status; }
}
