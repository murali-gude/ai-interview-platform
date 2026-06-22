package com.murali.aiinterview.service;

import com.murali.aiinterview.dto.AnswerRequest;
import com.murali.aiinterview.dto.SubmissionRequest;
import com.murali.aiinterview.entity.InterviewResult;
import com.murali.aiinterview.entity.Question;
import com.murali.aiinterview.exception.ResourceNotFoundException;
import com.murali.aiinterview.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {
    
    private final QuestionRepository questionRepository;
    private final InterviewResultService interviewResultService;
    private final MockAIService mockAIService;

    public EvaluationService(
            QuestionRepository questionRepository,
            InterviewResultService interviewResultService,
            MockAIService mockAIService
    ) {
        this.questionRepository = questionRepository;
        this.interviewResultService = interviewResultService;
        this.mockAIService = mockAIService;
    }

    public InterviewResult evaluateInterview(SubmissionRequest request) {

        int correctAnswers = 0;

        StringBuilder feedback = new StringBuilder();
        StringBuilder strengths = new StringBuilder();
        StringBuilder weaknesses = new StringBuilder();
        StringBuilder improvedAnswers = new StringBuilder();

        for (AnswerRequest answerRequest : request.getAnswers()) {

            Question question = questionRepository
                    .findById(answerRequest.getQuestionId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Question not found with id: "
                                            + answerRequest.getQuestionId()
                            )
                    );

            String userAnswer =
                    answerRequest.getUserAnswer() == null
                            ? ""
                            : answerRequest.getUserAnswer();

            if (userAnswer.length() > 20) {
                correctAnswers++;
            }

            feedback.append(mockAIService.generateFeedback(userAnswer))
                    .append("\n\n");

            strengths.append(mockAIService.generateStrengths(userAnswer))
                    .append("\n\n");

            weaknesses.append(mockAIService.generateWeaknesses(userAnswer))
                    .append("\n\n");

            improvedAnswers.append(
                            mockAIService.generateImprovedAnswer(userAnswer)
                    )
                    .append("\n\n");
        }

        InterviewResult result = new InterviewResult();

        result.setTotalQuestions(request.getAnswers().size());
        result.setCorrectAnswers(correctAnswers);

        double percentage =
                (correctAnswers * 100.0)
                        / request.getAnswers().size();

        result.setPercentage(percentage);

        if (percentage >= 60) {
            result.setResultStatus("PASS");
        } else {
            result.setResultStatus("FAIL");
        }

        result.setFeedback(feedback.toString());
        result.setStrengths(strengths.toString());
        result.setWeaknesses(weaknesses.toString());
        result.setImprovedAnswer(improvedAnswers.toString());

        return interviewResultService.saveResult(
                request.getUserId(),
                request.getInterviewId(),
                result
        );
    }

}
