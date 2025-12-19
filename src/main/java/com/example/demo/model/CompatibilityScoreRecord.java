package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compatibility_score_records", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"student_a_id", "student_b_id"}))
public class CompatibilityScoreRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_a_id", nullable = false)
    private Long studentAId;

    @Column(name = "student_b_id", nullable = false)
    private Long studentBId;

    @Column(nullable = false)
    private Double score;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompatibilityLevel compatibilityLevel;

    @Column(name = "computed_at")
    private LocalDateTime computedAt = LocalDateTime.now();

    @Column(columnDefinition = "TEXT")
    private String detailsJson;

    public enum CompatibilityLevel {
        LOW, MEDIUM, HIGH, EXCELLENT
    }

    public CompatibilityScoreRecord() {}

    public CompatibilityScoreRecord(Long studentAId, Long studentBId, Double score, 
                                   CompatibilityLevel compatibilityLevel, String detailsJson) {
        this.studentAId = studentAId;
        this.studentBId = studentBId;
        this.score = score;
        this.compatibilityLevel = compatibilityLevel;
        this.detailsJson = detailsJson;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentAId() { return studentAId; }
    public void setStudentAId(Long studentAId) { this.studentAId = studentAId; }

    public Long getStudentBId() { return studentBId; }
    public void setStudentBId(Long studentBId) { this.studentBId = studentBId; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public CompatibilityLevel getCompatibilityLevel() { return compatibilityLevel; }
    public void setCompatibilityLevel(CompatibilityLevel compatibilityLevel) { this.compatibilityLevel = compatibilityLevel; }

    public LocalDateTime getComputedAt() { return computedAt; }
    public void setComputedAt(LocalDateTime computedAt) { this.computedAt = computedAt; }

    public String getDetailsJson() { return detailsJson; }
    public void setDetailsJson(String detailsJson) { this.detailsJson = detailsJson; }
}