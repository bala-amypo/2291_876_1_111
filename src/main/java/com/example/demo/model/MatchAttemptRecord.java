package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_attempt_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchAttemptRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long initiatorStudentId;
    
    @Column(nullable = false)
    private Long candidateStudentId;
    
    private Long resultScoreId;
    
    @Enumerated(EnumType.STRING)
    private MatchStatus status;
    
    @Column(name = "attempted_at")
    private LocalDateTime attemptedAt;
    
    @PrePersist
    protected void onCreate() {
        attemptedAt = LocalDateTime.now();
    }
}

enum MatchStatus {
    MATCHED, NOT_COMPATIBLE, PENDING_REVIEW
}