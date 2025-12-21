package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.service.RoomAssignmentService;

@Service
@Transactional
public class RoomAssignmentServiceImpl implements RoomAssignmentService {

    private final RoomAssignmentRecordRepository repo;

    public RoomAssignmentServiceImpl(RoomAssignmentRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord record) {
        return repo.save(record);
    }

    @Override
    public RoomAssignmentRecord getAssignmentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room assignment not found"));
    }

    @Override
    public List<RoomAssignmentRecord> getAssignmentsForStudent(Long studentId) {
        return repo.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<RoomAssignmentRecord> getAllRoomAssignments() {
        return repo.findAll();
    }

    @Override
    public RoomAssignmentRecord updateRoomStatus(Long id, String status) {
        RoomAssignmentRecord r = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room assignment not found"));
        r.setStatus(RoomAssignmentRecord.Status.valueOf(status.toUpperCase()));
        return repo.save(r);
    }
}
