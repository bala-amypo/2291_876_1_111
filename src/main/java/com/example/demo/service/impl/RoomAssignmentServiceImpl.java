package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RoomAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomAssignmentServiceImpl implements RoomAssignmentService {
    private final RoomAssignmentRecordRepository assignmentRepository;
    private final StudentProfileRepository studentRepository;

    public RoomAssignmentServiceImpl(RoomAssignmentRecordRepository assignmentRepository,
                                   StudentProfileRepository studentRepository) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {
        StudentProfile studentA = studentRepository.findById(assignment.getStudentAId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        StudentProfile studentB = studentRepository.findById(assignment.getStudentBId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        if (!studentA.isActive() || !studentB.isActive()) {
            throw new IllegalArgumentException("both students must be active");
        }

        return assignmentRepository.save(assignment);
    }

    @Override
    public RoomAssignmentRecord getAssignmentById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId) {
        return assignmentRepository.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<RoomAssignmentRecord> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    @Override
    public RoomAssignmentRecord updateStatus(Long id, String status) {
        RoomAssignmentRecord assignment = getAssignmentById(id);
        assignment.setStatus(RoomAssignmentRecord.Status.valueOf(status));
        return assignmentRepository.save(assignment);
    }
}
