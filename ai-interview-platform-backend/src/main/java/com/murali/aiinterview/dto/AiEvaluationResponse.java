package com.murali.aiinterview.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class AiEvaluationResponse {

    @JsonPropertyDescription(
            "Whether the candidate answer is sufficiently correct. Use true when the overall score is 60 or above."
    )
    public boolean correct;

    @JsonPropertyDescription(
            "Overall answer score from 0 to 100."
    )
    public int score;

    @JsonPropertyDescription(
            "Technical correctness score from 0 to 100."
    )
    public int correctnessScore;

    @JsonPropertyDescription(
            "Relevance to the interview question, scored from 0 to 100."
    )
    public int relevanceScore;

    @JsonPropertyDescription(
            "Completeness of the answer, scored from 0 to 100."
    )
    public int completenessScore;

    @JsonPropertyDescription(
            "Clarity and understandability of the answer, scored from 0 to 100."
    )
    public int clarityScore;

    @JsonPropertyDescription(
            "The strongest and technically correct parts of the candidate answer."
    )
    public String strengths;

    @JsonPropertyDescription(
            "Important concepts or technical points missing from the candidate answer."
    )
    public String missingPoints;

    @JsonPropertyDescription(
            "Constructive feedback explaining how the candidate can improve the answer."
    )
    public String feedback;

    @JsonPropertyDescription(
            "A concise, accurate and interview-ready improved answer."
    )
    public String improvedAnswer;
}