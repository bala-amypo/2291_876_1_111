package com.example.demo.service.impl;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StudentProfile;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentRepo;
    private final UserAccountRepository userRepo;

    public StudentProfileServiceImpl(StudentProfileRepository studentRepo,
                                     UserAccountRepository userRepo) {
        this.studentRepo = studentRepo;
        this.userRepo = userRepo;
    }

    @Override
    public StudentProfile createStudent(StudentProfile profile) {
        return studentRepo.save(profile);
    }

    @Override
    public StudentProfile createProfile(StudentProfileDto dto, Long userId) {
        UserAccount user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        StudentProfile profile = new StudentProfile();
        profile.setUserAccount(user);
        mapDtoToEntity(dto, profile);
        return studentRepo.save(profile);
    }

    @Override
    public StudentProfile updateProfile(Long id, StudentProfileDto dto) {
        StudentProfile profile = getStudentById(id);
        mapDtoToEntity(dto, profile);
        return studentRepo.save(profile);
    }

    @Override
    public StudentProfile getProfile(Long id) {
        return getStudentById(id);
    }

    @Override
    public StudentProfile getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    // 🔥 THIS METHOD WAS MISSING / WRONG
    @Override
    public List<StudentProfile> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return studentRepo.findAll();
    }

    @Override
    public StudentProfile findByStudentId(String studentId) {
        return studentRepo.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public StudentProfile updateStudentStatus(Long id, boolean active) {
        StudentProfile profile = getStudentById(id);
        profile.setActive(active);
        return studentRepo.save(profile);
    }

    @Override
    public Optional<StudentProfile> getById(Long id) {
        return studentRepo.findById(id);
    }

    

private void mapDtoToEntity(StudentProfileDto dto, StudentProfile profile) {
    if (dto.getStudentId() != null)
        profile.setStudentId(String.valueOf(dto.getStudentId()));

    if (dto.getFullName() != null) profile.setFullName(dto.getFullName());
    if (dto.getAge() != null) profile.setAge(dto.getAge());
    if (dto.getCourse() != null) profile.setCourse(dto.getCourse());
    if (dto.getYearOfStudy() != null) profile.setYearOfStudy(dto.getYearOfStudy());
    if (dto.getGender() != null) profile.setGender(dto.getGender());
    if (dto.getRoomTypePreference() != null) profile.setRoomTypePreference(dto.getRoomTypePreference());
    if (dto.getDepartment() != null) profile.setDepartment(dto.getDepartment());
    if (dto.getYearLevel() != null) profile.setYearLevel(dto.getYearLevel());
    if (dto.getActive() != null) profile.setActive(dto.getActive());
}

}
