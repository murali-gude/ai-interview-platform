package com.murali.aiinterview.controller;

import com.murali.aiinterview.entity.InterviewResult;
import com.murali.aiinterview.service.InterviewResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class InterviewResultController {

    private final InterviewResultService resultService;

    public InterviewResultController(InterviewResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/user/{userId}/interview/{interviewId}")
    public InterviewResult saveResult(
            @PathVariable Long userId,
            @PathVariable Long interviewId,
            @RequestBody InterviewResult result
    ) {
        return resultService.saveResult(userId, interviewId, result);
    }

    @GetMapping
    public List<InterviewResult> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/user/{userId}")
    public List<InterviewResult> getResultsByUserId(@PathVariable Long userId) {
        return resultService.getResultsByUserId(userId);
    }
}