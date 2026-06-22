package com.murali.aiinterview.repository;

import com.murali.aiinterview.entity.AnswerEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerEvaluationRepository
        extends JpaRepository<AnswerEvaluation, Long> {

    List<AnswerEvaluation> findByInterviewResultId(
            Long interviewResultId
    );
}