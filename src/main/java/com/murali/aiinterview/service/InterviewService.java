package com.murali.aiinterview.service;

import com.murali.aiinterview.entity.Interview;
import com.murali.aiinterview.repository.InterviewRepository;
import org.springframework.stereotype.Service;
import com.murali.aiinterview.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;

    public InterviewService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
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
                                "Interview not found with id: " + id));
    }
    }
