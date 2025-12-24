
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_attempt_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchAttemptRecord {

    public enum Status {
    MATCHED,
    PENDING_REVIEW,
    FAILED
}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long initiatorStudentId;
    private Long candidateStudentId;
    private Long resultScoreId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime attemptedAt;

    @PrePersist
    void onCreate() {
        attemptedAt = LocalDateTime.now();
    }
}
