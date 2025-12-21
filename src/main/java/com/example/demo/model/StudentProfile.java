package com.example.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

@Entity
public class StudentProfile{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique=true)
    private String studentId;

    private String  fullName;

    @Column(unique=true)
    private String email;

    private String department;
    
    private Integer yearLevel;

    private Boolean active=true;
    
    private LocalDateTime createdAt;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public Integer getYearLevel() {
        return yearLevel;
    }
    public void setYearLevel(Integer yearLevel) {
        this.yearLevel = yearLevel;
    }

    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public StudentProfile(Long id,String studentId,String fullName,String email,String department,Integer yearLevel,Boolean active,LocalDateTime createdAt){
        this.id=id;
        this.studentId=studentId;
        this.fullName=fullName;
        this.email=email;
        this.department=department;
        this.yearLevel=yearLevel;
        this.active=active;
        this.createdAt=createdAt;
    }
    public StudentProfile(){

    }
}