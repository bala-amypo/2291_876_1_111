package com.example.demo.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
    long id;
    String studentId;
    String fullName;
    String email;
    String department;
    int yearLevel;
    boolean active;
    LocalDateTime createdAt;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public int getYearLevel() {
        return yearLevel;
    }
    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public Student(long id, String studentId, String fullName, String email, String department, boolean active,
            LocalDateTime createdAt) {
        this.id = id;
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.active = active;
        this.createdAt = createdAt;
    }
    public Student() {
    }

    

}