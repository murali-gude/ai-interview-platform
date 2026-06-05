package com.murali.aiinterview.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String technology;

    private String difficulty;

    private Integer numberOfQuestions;
}