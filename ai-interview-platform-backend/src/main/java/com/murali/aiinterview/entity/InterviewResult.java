package com.murali.aiinterview.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "interview_results")
public class InterviewResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalQuestions;

    private Integer correctAnswers;

    private Double percentage;

    private String resultStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "interview_id")
    private Interview interview;
}