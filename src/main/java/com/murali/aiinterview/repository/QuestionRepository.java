package com.murali.aiinterview.repository;

import com.murali.aiinterview.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}