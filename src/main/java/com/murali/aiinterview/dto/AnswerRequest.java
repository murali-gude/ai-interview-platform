package com.murali.aiinterview.dto;

import lombok.Data;

@Data
public class AnswerRequest {

    private Long questionId;
    private String userAnswer;
}