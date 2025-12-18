package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.springframework.beans.factory.config.YamlProcessor.MatchStatus;

@Entity
@Table(
    name = "match_attempt_record",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"initiatorStudentId", "candidateStudentId"})
    }
)
public class MatchAttemptRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long initiatorStudentId;

    private Long candidateStudentId;

    private Long resultScoreRecord;

    private String status;

    private LocalDateTime attemptedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInitiatorStudentId() {
        return initiatorStudentId;
    }

    public void setInitiatorStudentId(Long initiatorStudentId) {
        this.initiatorStudentId = initiatorStudentId;
    }

    public Long getCandidateStudentId() {
        return candidateStudentId;
    }

    public void setCandidateStudentId(Long candidateStudentId) {
        this.candidateStudentId = candidateStudentId;
    }

    public Long getResultScoreRecord() {
        return resultScoreRecord;
    }

    public void setResultScoreRecord(Long resultScoreRecord) {
        this.resultScoreRecord = resultScoreRecord;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }

    public void setAttemptedAt(LocalDateTime attemptedAt) {
        this.attemptedAt = attemptedAt;
    }

    public MatchAttemptRecord(Long id, Long initiatorStudentId, Long candidateStudentId, Long resultScoreRecord,
            String status, LocalDateTime attemptedAt) {
        this.id = id;
        this.initiatorStudentId = initiatorStudentId;
        this.candidateStudentId = candidateStudentId;
        this.resultScoreRecord = resultScoreRecord;
        this.status = status;
        this.attemptedAt = attemptedAt;
    }

    
    public MatchAttemptRecord() {
    }
}