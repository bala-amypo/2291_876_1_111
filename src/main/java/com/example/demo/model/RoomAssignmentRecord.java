package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "room_assignment_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomAssignmentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String roomNumber;
    
    @Column(nullable = false)
    private Long studentAId;
    
    @Column(nullable = false)
    private Long studentBId;
    
    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;
    
    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;
    
    @PrePersist
    protected void onCreate() {
        assignedAt = LocalDateTime.now();
    }
}

enum AssignmentStatus {
    ACTIVE, COMPLETED, CANCELLED
}

