package com.example.demo.service;

import com.example.demo.model.RoomAssignmentRecord;
import java.util.List;

public interface RoomAssignmentService {
    RoomAssignmentRecord assignRoom(RoomAssignmentRecord record);
    RoomAssignmentRecord updateRoomStatus(Long id, String status);
    List<RoomAssignmentRecord> getAssignmentsForStudent(Long studentId);
    RoomAssignmentRecord getAssignmentById(Long id);
    List<RoomAssignmentRecord> getAllRoomAssignments();
}