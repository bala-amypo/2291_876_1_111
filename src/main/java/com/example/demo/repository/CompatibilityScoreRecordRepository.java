package com.example.demo.repository;

import com.example.demo.model.CompatibilityScoreRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompatibilityScoreRecordRepository extends JpaRepository<CompatibilityScoreRecord, Long> {
    Optional<CompatibilityScoreRecord> findByStudentAIdAndStudentBId(Long id1, Long id2);
    List<CompatibilityScoreRecord> findByStudentAIdOrStudentBId(Long id1, Long id2);
}
