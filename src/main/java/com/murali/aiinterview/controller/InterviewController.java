package com.murali.aiinterview.controller;

import com.murali.aiinterview.dto.QuestionResponse;
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

    @GetMapping("/start/{id}")
    public StartInterviewResponse startInterview(@PathVariable Long id) {

        Interview interview = interviewService.getInterviewById(id);

        StartInterviewResponse response = new StartInterviewResponse();

        response.setInterviewId(interview.getId());
        response.setTitle(interview.getTitle());
        response.setTechnology(interview.getTechnology());
        response.setDifficulty(interview.getDifficulty());

        List<QuestionResponse> questions = interview.getQuestions()
                .stream()
                .map(q -> {
                    QuestionResponse qr = new QuestionResponse();
                    qr.setId(q.getId());
                    qr.setQuestionText(q.getQuestionText());
                    qr.setTechnology(q.getTechnology());
                    return qr;
                })
                .toList();

        response.setQuestions(questions);

        return response;
    }
}