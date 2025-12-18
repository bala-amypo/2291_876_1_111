package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.service.CompatibilityScoreService;

@Service
public class CompatibilityScoreServiceImplement
        implements CompatibilityScoreService {

    private final CompatibilityScoreRecordRepository repository;

    public CompatibilityScoreServiceImplement(
            CompatibilityScoreRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompatibilityScoreRecord computeScore(
            CompatibilityScoreRecord record) {

        if (record.getStudentAId().equals(record.getStudentBId())) {
            throw new RuntimeException("same student cannot be matched");
        }

        if (repository.existsByStudentAIdAndStudentBId(
                record.getStudentAId(),
                record.getStudentBId())) {
            throw new RuntimeException("score already computed");
        }

        return repository.save(record);
    }
}
