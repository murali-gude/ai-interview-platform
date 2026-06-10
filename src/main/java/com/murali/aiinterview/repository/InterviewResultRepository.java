package com.murali.aiinterview.repository;

import com.murali.aiinterview.entity.InterviewResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewResultRepository extends JpaRepository<InterviewResult, Long> {

    List<InterviewResult> findByUserId(Long userId);
}