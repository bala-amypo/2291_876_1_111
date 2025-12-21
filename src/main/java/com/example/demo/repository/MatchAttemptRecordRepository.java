package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MatchAttemptRecord;

@Repository
public interface MatchAttemptRecordRepository extends JpaRepository<MatchAttemptRecord, Long> {

    List<MatchAttemptRecord> findByInitiatorStudentIdOrCandidateStudentId(Long id1, Long id2);
}
