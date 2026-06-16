package com.murali.aiinterview.service;

import com.murali.aiinterview.entity.Question;
import com.murali.aiinterview.exception.ResourceNotFoundException;
import com.murali.aiinterview.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Question not found with id: " + id
                        ));
    }

    public List<Question> getQuestionsByTechnology(String technology) {
        return questionRepository.findByTechnology(technology);
    }

    public List<Question> getQuestionsByInterviewId(Long interviewId) {
        return questionRepository.findByInterviewId(interviewId);
    }

    public Question updateQuestion(Long id, Question questionDetails) {
        Question existingQuestion = getQuestionById(id);

        existingQuestion.setQuestionText(questionDetails.getQuestionText());
        existingQuestion.setAnswer(questionDetails.getAnswer());
        existingQuestion.setTechnology(questionDetails.getTechnology());
        existingQuestion.setInterview(questionDetails.getInterview());

        return questionRepository.save(existingQuestion);
    }

    public String deleteQuestion(Long id) {
        Question existingQuestion = getQuestionById(id);
        questionRepository.delete(existingQuestion);
        return "Question deleted successfully";
    }
}