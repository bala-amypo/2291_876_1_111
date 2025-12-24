package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.MatchAttemptRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.service.MatchAttemptService;

@Service
public class MatchAttemptServiceImpl implements MatchAttemptService {

    private final MatchAttemptRecordRepository repo;
    private final CompatibilityScoreRecordRepository scoreRepo;

    public MatchAttemptServiceImpl(
            MatchAttemptRecordRepository repo,
            CompatibilityScoreRecordRepository scoreRepo) {
        this.repo = repo;
        this.scoreRepo = scoreRepo;
    }

    public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord a) {
        if (a.getResultScoreId() != null &&
                scoreRepo.findById(a.getResultScoreId()).isPresent()) {
            a.setStatus(MatchAttemptRecord.Status.MATCHED);
        } else {
            a.setStatus(MatchAttemptRecord.Status.PENDING_REVIEW);
        }
        return repo.save(a);
    }

    public MatchAttemptRecord updateAttemptStatus(Long id, String status) {
        MatchAttemptRecord a = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        a.setStatus(MatchAttemptRecord.Status.valueOf(status));
        return repo.save(a);
    }

    public List<MatchAttemptRecord> getAttemptsByStudent(Long id) {
        return repo.findByInitiatorStudentIdOrCandidateStudentId(id, id);
    }

    public List<MatchAttemptRecord> getAllMatchAttempts() {
        return repo.findAll();
    }
}
