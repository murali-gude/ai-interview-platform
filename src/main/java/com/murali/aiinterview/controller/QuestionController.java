package com.murali.aiinterview.controller;

import com.murali.aiinterview.dto.ApiResponse;
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
    public ApiResponse<Question> createQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestion(question);

        return new ApiResponse<>(
                true,
                "Question created successfully",
                savedQuestion
        );
    }

    @GetMapping
    public ApiResponse<List<Question>> getAllQuestions() {
        return new ApiResponse<>(
                true,
                "Questions fetched successfully",
                questionService.getAllQuestions()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<Question> getQuestionById(@PathVariable Long id) {
        return new ApiResponse<>(
                true,
                "Question fetched successfully",
                questionService.getQuestionById(id)
        );
    }

    @GetMapping("/technology/{technology}")
    public ApiResponse<List<Question>> getQuestionsByTechnology(
            @PathVariable String technology) {

        return new ApiResponse<>(
                true,
                "Questions fetched successfully",
                questionService.getQuestionsByTechnology(technology)
        );
    }

    @GetMapping("/interview/{interviewId}")
    public ApiResponse<List<Question>> getQuestionsByInterviewId(
            @PathVariable Long interviewId) {

        return new ApiResponse<>(
                true,
                "Questions fetched successfully",
                questionService.getQuestionsByInterviewId(interviewId)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<Question> updateQuestion(
            @PathVariable Long id,
            @RequestBody Question questionDetails) {

        return new ApiResponse<>(
                true,
                "Question updated successfully",
                questionService.updateQuestion(id, questionDetails)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteQuestion(@PathVariable Long id) {
        String result = questionService.deleteQuestion(id);

        return new ApiResponse<>(
                true,
                result,
                result
        );
    }
}