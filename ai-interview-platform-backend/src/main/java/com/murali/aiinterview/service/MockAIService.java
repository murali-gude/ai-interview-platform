package com.murali.aiinterview.service;

import org.springframework.stereotype.Service;

@Service
public class MockAIService {

    public String generateFeedback(String answer) {

        if (answer == null || answer.isBlank()) {
            return "No answer provided.";
        }

        return "Good attempt. Answer covers the basic concept but can be improved with additional technical details.";
    }

    public String generateStrengths(String answer) {

        if (answer == null || answer.isBlank()) {
            return "No strengths identified.";
        }

        return "Shows understanding of the topic and attempts to answer the question.";
    }

    public String generateWeaknesses(String answer) {

        if (answer == null || answer.isBlank()) {
            return "Answer not provided.";
        }

        return "Needs more technical explanation, examples and deeper understanding.";
    }

    public String generateImprovedAnswer(String answer) {

        if (answer == null || answer.isBlank()) {
            return "Please provide an answer.";
        }

        return answer +
                "\n\nImproved Version:\n" +
                "This answer can be strengthened by adding technical concepts, practical examples and real-world usage scenarios.";
    }
}