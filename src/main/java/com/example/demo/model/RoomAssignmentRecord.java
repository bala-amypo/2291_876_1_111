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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private Long studentAId;

    @Column(nullable = false)
    private Long studentBId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime assignedAt = LocalDateTime.now();

    @Column(nullable = false)
    private String status;

    @PrePersist
    protected void onCreate() {
        assignedAt = LocalDateTime.now();
    }
}