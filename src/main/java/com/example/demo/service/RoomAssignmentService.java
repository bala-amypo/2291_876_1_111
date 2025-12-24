package com.example.demo.service;

import com.example.demo.model.RoomAssignmentRecord;
import java.util.*;

public interface RoomAssignmentService {
    RoomAssignmentRecord assignRoom(RoomAssignmentRecord r);
    RoomAssignmentRecord updateStatus(Long id, String status);
    List<RoomAssignmentRecord> getAssignmentsByStudent(Long id);
    RoomAssignmentRecord getAssignmentById(Long id);
    List<RoomAssignmentRecord> getAllAssignments();
}
