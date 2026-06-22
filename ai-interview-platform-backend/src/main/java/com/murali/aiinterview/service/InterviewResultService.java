package com.murali.aiinterview.service;

import com.murali.aiinterview.entity.Interview;
import com.murali.aiinterview.entity.InterviewResult;
import com.murali.aiinterview.entity.User;
import com.murali.aiinterview.exception.ResourceNotFoundException;
import com.murali.aiinterview.repository.InterviewRepository;
import com.murali.aiinterview.repository.InterviewResultRepository;
import com.murali.aiinterview.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewResultService {

    private final InterviewResultRepository resultRepository;
    private final UserRepository userRepository;
    private final InterviewRepository interviewRepository;

    public InterviewResultService(
            InterviewResultRepository resultRepository,
            UserRepository userRepository,
            InterviewRepository interviewRepository
    ) {
        this.resultRepository = resultRepository;
        this.userRepository = userRepository;
        this.interviewRepository = interviewRepository;
    }

    public InterviewResult saveResult(
            Long userId,
            Long interviewId,
            InterviewResult result
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId
                        )
                );

        Interview interview = interviewRepository
                .findById(interviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Interview not found with id: "
                                        + interviewId
                        )
                );

        if (result.getTotalQuestions() == null ||
                result.getTotalQuestions() <= 0) {

            throw new IllegalArgumentException(
                    "Total questions must be greater than zero."
            );
        }

        /*
         * AI EvaluationService already calculates percentage
         * using the average AI score.
         *
         * This fallback keeps compatibility with older evaluation logic.
         */
        double percentage;

        if (result.getPercentage() != null) {
            percentage = result.getPercentage();
        } else {
            percentage =
                    result.getCorrectAnswers() * 100.0
                            / result.getTotalQuestions();
        }

        percentage = Math.max(0, Math.min(100, percentage));

        result.setPercentage(percentage);

        if (percentage >= 60) {
            result.setResultStatus("PASS");
        } else {
            result.setResultStatus("FAIL");
        }

        result.setUser(user);
        result.setInterview(interview);

        return resultRepository.save(result);
    }

    public List<InterviewResult> getAllResults() {
        return resultRepository.findAll();
    }

    public List<InterviewResult> getResultsByUserId(Long userId) {
        return resultRepository.findByUserId(userId);
    }
}

