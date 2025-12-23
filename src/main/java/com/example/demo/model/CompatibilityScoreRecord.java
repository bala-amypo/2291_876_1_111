package com.example.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.time.LocalDateTime;
@Entity

public class CompatibilityScoreRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentAId;
    private Long studentBId;
    private Double score;

    @Enumerated(EnumType.STRING)
    private CompatibilityLevel compatibilityLevel;

    private LocalDateTime computedAt;

    @Column(columnDefinition = "TEXT")
    private String detailsJson;

    public enum CompatibilityLevel {
        LOW, MEDIUM, HIGH, EXCELLENT
    }

    public CompatibilityScoreRecord() {}

    public CompatibilityScoreRecord(Long id, Long studentAId, Long studentBId,
                                    Double score, CompatibilityLevel compatibilityLevel,
                                    LocalDateTime computedAt, String detailsJson) {
        this.id = id;
        this.studentAId = studentAId;
        this.studentBId = studentBId;
        this.score = score;
        this.compatibilityLevel = compatibilityLevel;
        this.computedAt = computedAt;
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
    public void setCompatibilityLevel(CompatibilityLevel compatibilityLevel) {
        this.compatibilityLevel = compatibilityLevel;
    }

    public LocalDateTime getComputedAt() { return computedAt; }
    public void setComputedAt(LocalDateTime computedAt) { this.computedAt = computedAt; }

    public String getDetailsJson() { return detailsJson; }
    public void setDetailsJson(String detailsJson) { this.detailsJson = detailsJson; }
}
