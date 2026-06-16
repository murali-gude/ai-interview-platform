package com.murali.aiinterview.controller;

import com.murali.aiinterview.dto.ApiResponse;
import com.murali.aiinterview.dto.StartInterviewResponse;
import com.murali.aiinterview.entity.Interview;
import com.murali.aiinterview.service.InterviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping
    public Interview createInterview(@RequestBody Interview interview) {
        return interviewService.saveInterview(interview);
    }

    @GetMapping
    public List<Interview> getAllInterviews() {
        return interviewService.getAllInterviews();
    }

    @GetMapping("/{id}")
    public Interview getInterviewById(@PathVariable Long id) {
        return interviewService.getInterviewById(id);
    }

    @PostMapping("/{id}/start")
    public ApiResponse<StartInterviewResponse> startInterview(@PathVariable Long id) {

        StartInterviewResponse response = interviewService.startInterview(id);

        return new ApiResponse<>(
                true,
                "Interview started successfully",
                response
        );
    }
}