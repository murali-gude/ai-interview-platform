package com.murali.aiinterview.repository;

import com.murali.aiinterview.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByTechnology(String technology);

    List<Question> findByInterviewId(Long interviewId);
}