package com.murali.aiinterview.service;

import com.murali.aiinterview.dto.AnswerRequest;
import com.murali.aiinterview.dto.SubmissionRequest;
import com.murali.aiinterview.entity.InterviewResult;
import com.murali.aiinterview.entity.Question;
import com.murali.aiinterview.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {

    private final QuestionRepository questionRepository;
    private final InterviewResultService interviewResultService;

    public EvaluationService(
            QuestionRepository questionRepository,
            InterviewResultService interviewResultService
    ) {
        this.questionRepository = questionRepository;
        this.interviewResultService = interviewResultService;
    }

    public InterviewResult evaluateInterview(SubmissionRequest request) {

        int correctAnswers = 0;

        for (AnswerRequest answerRequest : request.getAnswers()) {

            Question question = questionRepository
                    .findById(answerRequest.getQuestionId())
                    .orElse(null);

            if (question != null &&
                    question.getAnswer() != null &&
                    answerRequest.getUserAnswer() != null &&
                    question.getAnswer().equalsIgnoreCase(
                            answerRequest.getUserAnswer())) {

                correctAnswers++;
            }
        }

        InterviewResult result = new InterviewResult();

        result.setTotalQuestions(request.getAnswers().size());
        result.setCorrectAnswers(correctAnswers);

        return interviewResultService.saveResult(
                request.getUserId(),
                request.getInterviewId(),
                result
        );
    }
}