package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {
    private final CompatibilityScoreRecordRepository scoreRepo;
    private final HabitProfileRepository habitRepo;
    
    public CompatibilityScoreServiceImpl(CompatibilityScoreRecordRepository scoreRepo,
                                         HabitProfileRepository habitRepo) {
        this.scoreRepo = scoreRepo;
        this.habitRepo = habitRepo;
    }
    
    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {
        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("same student");
        }
        
        HabitProfile habitA = habitRepo.findByStudentId(studentAId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found for student A"));
        HabitProfile habitB = habitRepo.findByStudentId(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found for student B"));
        
        double score = calculateCompatibility(habitA, habitB);
        
        var existing = scoreRepo.findByStudentAIdAndStudentBId(studentAId, studentBId);
        
        CompatibilityScoreRecord record;
        if (existing.isPresent()) {
            record = existing.get();
            record.setScore(score);
        } else {
            record = CompatibilityScoreRecord.builder()
                    .studentAId(studentAId)
                    .studentBId(studentBId)
                    .score(score)
                    .compatibilityLevel(determineLevel(score))
                    .build();
        }
        
        return scoreRepo.save(record);
    }
    
    private double calculateCompatibility(HabitProfile a, HabitProfile b) {
        double score = 100.0;
        
        // Sleep schedule match
        if (!a.getSleepSchedule().equals(b.getSleepSchedule())) {
            score -= 20;
        }
        
        // Cleanliness match
        if (!a.getCleanlinessLevel().equals(b.getCleanlinessLevel())) {
            score -= 15;
        }
        
        // Noise tolerance match
        if (!a.getNoiseTolerance().equals(b.getNoiseTolerance())) {
            score -= 15;
        }
        
        // Social preference match
        if (!a.getSocialPreference().equals(b.getSocialPreference())) {
            score -= 10;
        }
        
        // Study hours similarity
        int studyDiff = Math.abs(a.getStudyHoursPerDay() - b.getStudyHoursPerDay());
        if (studyDiff > 3) {
            score -= 10;
        }
        
        return Math.max(0, Math.min(100, score));
    }
    
    private CompatibilityLevel determineLevel(double score) {
        if (score >= 80) return CompatibilityLevel.EXCELLENT;
        if (score >= 60) return CompatibilityLevel.HIGH;
        if (score >= 40) return CompatibilityLevel.MEDIUM;
        return CompatibilityLevel.LOW;
    }
    
    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
    }
    
    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepo.findByStudentAIdOrStudentBIdOrderByScoreDesc(studentId, studentId);
    }
    
    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}

// ========================

// 5. RoomAssignmentService.java (com.example.demo.service)
package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AssignmentStatus;
import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomAssignmentService {
    private final RoomAssignmentRecordRepository assignmentRepo;
    private final StudentProfileRepository studentRepo;
    
    public RoomAssignmentService(RoomAssignmentRecordRepository assignmentRepo,
                                 StudentProfileRepository studentRepo) {
        this.assignmentRepo = assignmentRepo;
        this.studentRepo = studentRepo;
    }
    
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
}
