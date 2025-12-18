package com.example.demo.model;
import java.time.LocalDateTime;

import org.springframework.beans.factory.config.YamlProcessor.MatchStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "match_attempts")
public class MatchAttemptRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long initiatorStudentId;

    private Long candidateStudentId;

    private Long resultScoreId;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

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

    public Long getResultScoreId() {
        return resultScoreId;
    }

    public void setResultScoreId(Long resultScoreId) {
        this.resultScoreId = resultScoreId;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }

    public void setAttemptedAt(LocalDateTime attemptedAt) {
        this.attemptedAt = attemptedAt;
    }

    public MatchAttemptRecord(Long id, Long initiatorStudentId, Long candidateStudentId, Long resultScoreId,
            MatchStatus status, LocalDateTime attemptedAt) {
        this.id = id;
        this.initiatorStudentId = initiatorStudentId;
        this.candidateStudentId = candidateStudentId;
        this.resultScoreId = resultScoreId;
        this.status = status;
        this.attemptedAt = attemptedAt;
    }

    public MatchAttemptRecord() {
    }
    

}