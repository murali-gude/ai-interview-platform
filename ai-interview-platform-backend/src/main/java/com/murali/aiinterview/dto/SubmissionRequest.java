package com.murali.aiinterview.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubmissionRequest {

    private Long userId;
    private Long interviewId;

    private List<AnswerRequest> answers;
}