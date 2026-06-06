package com.murali.aiinterview.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String technology;
    private String difficulty;
    private int numberOfQuestions;

    @OneToMany(mappedBy = "interview",
            cascade = CascadeType.ALL)
    private List<Question> questions;
}