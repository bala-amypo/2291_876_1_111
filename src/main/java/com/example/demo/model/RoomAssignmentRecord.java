package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Entity
@Table(name = "room_assignment_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomAssignmentRecord {

    public enum Status {
    ACTIVE,
    COMPLETED,
    CANCELLED
}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private Long studentAId;
    private Long studentBId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime assignedAt;

    @PrePersist
    void onCreate() {
        assignedAt = LocalDateTime.now();
    }
}
