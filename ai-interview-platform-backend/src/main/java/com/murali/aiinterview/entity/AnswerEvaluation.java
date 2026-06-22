package com.murali.aiinterview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "answer_evaluations")
public class AnswerEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long questionId;

    @Column(nullable = false, length = 1000)
    private String questionText;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String userAnswer;

    /*
     * Overall AI score from 0 to 100.
     */
    private Integer score;

    /*
     * Individual scoring categories from 0 to 100.
     */
    private Integer correctnessScore;

    private Integer relevanceScore;

    private Integer completenessScore;

    private Integer clarityScore;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String strengths;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String missingPoints;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String feedback;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String improvedAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "interview_result_id",
            nullable = false
    )
    @JsonIgnore
    private InterviewResult interviewResult;
}