package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_profile", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"studentId"}),
    @UniqueConstraint(columnNames = {"email"})
})
@Data
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String department;

    private Integer yearLevel;

    private boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();
}
