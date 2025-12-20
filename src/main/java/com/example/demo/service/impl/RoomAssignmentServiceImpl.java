package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AssignmentStatus;
import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomAssignmentServiceImpl implements RoomAssignmentService {
    private final RoomAssignmentRecordRepository assignmentRepo;
    private final StudentProfileRepository studentRepo;
    
    public RoomAssignmentServiceImpl(RoomAssignmentRecordRepository assignmentRepo,
                                     StudentProfileRepository studentRepo) {
        this.assignmentRepo = assignmentRepo;
        this.studentRepo = studentRepo;
    }
    
    @Override
    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {
        var studentA = studentRepo.findById(assignment.getStudentAId())
                .orElseThrow(() -> new ResourceNotFoundException("Student A not found"));
        var studentB = studentRepo.findById(assignment.getStudentBId())
                .orElseThrow(() -> new ResourceNotFoundException("Student B not found"));
        
        if (!studentA.getActive() || !studentB.getActive()) {
            throw new IllegalArgumentException("both students must be active");
        }
        
        return assignmentRepo.save(assignment);
    }
    
    @Override
    public RoomAssignmentRecord getAssignmentById(Long id) {
        return assignmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));
    }
    
    @Override
    public List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId) {
        return assignmentRepo.findByStudentAIdOrStudentBId(studentId, studentId);
    }
    
    @Override
    public List<RoomAssignmentRecord> getAllAssignments() {
        return assignmentRepo.findAll();
    }
    
    @Override
    public RoomAssignmentRecord updateStatus(Long id, String status) {
        RoomAssignmentRecord assignment = getAssignmentById(id);
        assignment.setStatus(AssignmentStatus.valueOf(status));
        return assignmentRepo.save(assignment);
    }
}