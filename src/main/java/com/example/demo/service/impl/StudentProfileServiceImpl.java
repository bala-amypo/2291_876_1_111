package com.example.demo.service.impl;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StudentProfile;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;
    private final UserAccountRepository userAccountRepository;

    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository, 
                                    UserAccountRepository userAccountRepository) {
        this.studentProfileRepository = studentProfileRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public StudentProfile createStudent(StudentProfile profile) {
        if (profile.getStudentId() != null && studentProfileRepository.existsByStudentId(profile.getStudentId())) {
            throw new IllegalArgumentException("studentId exists");
        }
        validateProfile(profile);
        return studentProfileRepository.save(profile);
    }

    @Override
    public StudentProfile createProfile(StudentProfileDto dto, Long userId) {
        UserAccount user = userAccountRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (dto.getStudentId() != null && studentProfileRepository.existsByStudentId(dto.getStudentId())) {
            throw new IllegalArgumentException("studentId exists");
        }

        StudentProfile profile = new StudentProfile();
        profile.setUserAccount(user);
        mapDtoToEntity(dto, profile);
        validateProfile(profile);

        return studentProfileRepository.save(profile);
    }

    @Override
    public StudentProfile updateProfile(Long id, StudentProfileDto dto) {
        StudentProfile profile = studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        mapDtoToEntity(dto, profile);
        validateProfile(profile);

        return studentProfileRepository.save(profile);
    }

    @Override
    public StudentProfile getProfile(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public StudentProfile getStudentById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return studentProfileRepository.findAll();
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    @Override
    public StudentProfile findByStudentId(String studentId) {
        return studentProfileRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public StudentProfile updateStudentStatus(Long id, boolean active) {
        StudentProfile profile = getStudentById(id);
        profile.setActive(active);
        return studentProfileRepository.save(profile);
    }

    private void mapDtoToEntity(StudentProfileDto dto, StudentProfile profile) {
        if (dto.getStudentId() != null) profile.setStudentId(dto.getStudentId());
        if (dto.getFullName() != null) profile.setFullName(dto.getFullName());
        if (dto.getAge() != null) profile.setAge(dto.getAge());
        if (dto.getCourse() != null) profile.setCourse(dto.getCourse());
        if (dto.getYearOfStudy() != null) profile.setYearOfStudy(dto.getYearOfStudy());
        if (dto.getGender() != null) profile.setGender(dto.getGender());
        if (dto.getRoomTypePreference() != null) profile.setRoomTypePreference(dto.getRoomTypePreference());
        if (dto.getSleepTime() != null) profile.setSleepTime(dto.getSleepTime());
        if (dto.getWakeTime() != null) profile.setWakeTime(dto.getWakeTime());
        if (dto.getSmoking() != null) profile.setSmoking(dto.getSmoking());
        if (dto.getDrinking() != null) profile.setDrinking(dto.getDrinking());
        if (dto.getNoiseTolerance() != null) profile.setNoiseTolerance(dto.getNoiseTolerance());
        if (dto.getStudyTime() != null) profile.setStudyTime(dto.getStudyTime());
        if (dto.getDepartment() != null) profile.setDepartment(dto.getDepartment());
        if (dto.getYearLevel() != null) profile.setYearLevel(dto.getYearLevel());
        if (dto.getActive() != null) profile.setActive(dto.getActive());
    }

    private void validateProfile(StudentProfile profile) {
        if (profile.getAge() != null && profile.getAge() <= 0) {
            throw new IllegalArgumentException("age must be > 0");
        }
        if (profile.getYearOfStudy() != null && profile.getYearOfStudy() <= 0) {
            throw new IllegalArgumentException("year must be > 0");
        }
        if (profile.getRoomTypePreference() != null) {
            String roomType = profile.getRoomTypePreference().toUpperCase();
            if (!roomType.equals("SINGLE") && !roomType.equals("DOUBLE") && !roomType.equals("TRIPLE")) {
                throw new IllegalArgumentException("Invalid room type preference");
            }
        }
    }
}