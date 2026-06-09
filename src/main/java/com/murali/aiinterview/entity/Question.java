package com.murali.aiinterview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private String answer;
    private String technology;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "interview_id")
    private Interview interview;
}