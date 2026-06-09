package com.murali.aiinterview.controller;

import com.murali.aiinterview.entity.Question;
import com.murali.aiinterview.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/interview/{interviewId}")
    public Question addQuestionToInterview(
            @PathVariable Long interviewId,
            @RequestBody Question question
    ) {
        return questionService.addQuestionToInterview(interviewId, question);
    }

    @GetMapping("/interview/{interviewId}")
    public List<Question> getQuestionsByInterviewId(@PathVariable Long interviewId) {
        return questionService.getQuestionsByInterviewId(interviewId);
    }
}