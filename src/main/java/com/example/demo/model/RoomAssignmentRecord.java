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

    private LocalDateTime assignedAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status status;

    protected RoomAssignmentRecord() {}

    public RoomAssignmentRecord(String roomNumber, Long studentAId,
                                Long studentBId, Status status) {
        this.roomNumber = roomNumber;
        this.studentAId = studentAId;
        this.studentBId = studentBId;
        this.status = status;
        this.assignedAt = LocalDateTime.now();
    }

    public enum Status {
        ACTIVE,
        COMPLETED,
        CANCELLED
    }
}
