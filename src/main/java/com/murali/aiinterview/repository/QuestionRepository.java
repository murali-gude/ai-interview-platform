package com.murali.aiinterview.repository;

import com.murali.aiinterview.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByInterviewId(Long interviewId);
}