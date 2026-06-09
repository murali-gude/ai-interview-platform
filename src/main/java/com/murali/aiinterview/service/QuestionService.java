package com.murali.aiinterview.service;

import com.murali.aiinterview.entity.Interview;
import com.murali.aiinterview.entity.Question;
import com.murali.aiinterview.repository.InterviewRepository;
import com.murali.aiinterview.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final InterviewRepository interviewRepository;

    public QuestionService(
            QuestionRepository questionRepository,
            InterviewRepository interviewRepository
    ) {
        this.questionRepository = questionRepository;
        this.interviewRepository = interviewRepository;
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question addQuestionToInterview(Long interviewId, Question question) {
        Interview interview = interviewRepository.findById(interviewId).orElse(null);

        if (interview == null) {
            return null;
        }

        question.setInterview(interview);
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByInterviewId(Long interviewId) {
        return questionRepository.findByInterviewId(interviewId);
    }
}