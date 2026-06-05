package com.murali.aiinterview.repository;

import com.murali.aiinterview.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

}