package com.murali.aiinterview.dto;

import lombok.Data;

import java.util.List;

@Data
public class StartInterviewResponse {

    private Long interviewId;
    private String title;
    private String technology;
    private String difficulty;
    private List<QuestionResponse> questions;
}