package com.example.demo.model.RoomAssignmentRecord;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class RoomAssignmentRecord{
    private Long id;
    private String roomNumber;
    private Long studentAId;
     private Long studentBId;
    private LocalDateTime assignedAt;

    @Enumerated(EnumType.STRING)
    private RoomAssignmentStatus status;
}