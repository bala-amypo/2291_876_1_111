package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.MatchAttemptRecord;

public interface MatchAttemptRecordRepository extends JpaRepository<MatchAttemptRecord,Long> {

}
