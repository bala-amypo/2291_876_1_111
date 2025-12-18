package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.CompatibilityScoreRecord;

public interface CompatibilityScoreRecordRepository
        extends JpaRepository<CompatibilityScoreRecord, Long> {

    boolean existsByStudentAIdAndStudentBId(Long studentAId, Long studentBId);
}
