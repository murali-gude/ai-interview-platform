package com.murali.aiinterview.controller;

import com.murali.aiinterview.dto.SubmissionRequest;
import com.murali.aiinterview.entity.InterviewResult;
import com.murali.aiinterview.service.EvaluationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public InterviewResult evaluateInterview(@RequestBody SubmissionRequest request) {
        return evaluationService.evaluateInterview(request);
    }
}