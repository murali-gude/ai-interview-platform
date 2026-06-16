package com.murali.aiinterview.service;

import com.murali.aiinterview.dto.QuestionResponse;
import com.murali.aiinterview.dto.StartInterviewResponse;
import com.murali.aiinterview.entity.Interview;
import com.murali.aiinterview.entity.Question;
import com.murali.aiinterview.exception.ResourceNotFoundException;
import com.murali.aiinterview.repository.InterviewRepository;
import com.murali.aiinterview.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final QuestionRepository questionRepository;

    public InterviewService(
            InterviewRepository interviewRepository,
            QuestionRepository questionRepository
    ) {
        this.interviewRepository = interviewRepository;
        this.questionRepository = questionRepository;
    }

    public Interview saveInterview(Interview interview) {
        return interviewRepository.save(interview);
    }

    public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }

    public Interview getInterviewById(Long id) {
        return interviewRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Interview not found with id: " + id
                        ));
    }

    public StartInterviewResponse startInterview(Long interviewId) {

        Interview interview = getInterviewById(interviewId);

        List<Question> questions =
                questionRepository.findByTechnology(interview.getTechnology())
                        .stream()
                        .limit(interview.getNumberOfQuestions())
                        .toList();

        List<QuestionResponse> questionResponses = new ArrayList<>();

        for (Question question : questions) {
            QuestionResponse questionResponse = new QuestionResponse();

            questionResponse.setId(question.getId());
            questionResponse.setQuestionText(question.getQuestionText());
            questionResponse.setTechnology(question.getTechnology());

            questionResponses.add(questionResponse);
        }

        StartInterviewResponse response = new StartInterviewResponse();

        response.setInterviewId(interview.getId());
        response.setTitle(interview.getTitle());
        response.setTechnology(interview.getTechnology());
        response.setDifficulty(interview.getDifficulty());
        response.setQuestions(questionResponses);

        return response;
    }
}