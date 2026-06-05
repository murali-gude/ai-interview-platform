package com.murali.aiinterview.controller;

import com.murali.aiinterview.entity.Interview;
import com.murali.aiinterview.repository.InterviewRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewController {

    private final InterviewRepository interviewRepository;

    public InterviewController(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    @PostMapping
    public Interview createInterview(@RequestBody Interview interview) {
        return interviewRepository.save(interview);
    }

    @GetMapping
    public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }
}