import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import API from "../services/api";

function StartInterview() {
    const { interviewId } = useParams();
    const navigate = useNavigate();

    const [interview, setInterview] = useState(null);
    const [answers, setAnswers] = useState({});
    const [message, setMessage] = useState("Loading interview...");
    const [submitting, setSubmitting] = useState(false);

    useEffect(() => {
        const loadInterview = async () => {
            try {
                const response = await API.post(
                    `/interviews/${interviewId}/start`
                );

                const interviewData = response.data?.data ?? response.data;

                setInterview(interviewData);
                setMessage("");
            } catch (error) {
                setMessage(
                    error.response?.data?.message ||
                    "Unable to start the interview."
                );
            }
        };

        loadInterview();
    }, [interviewId]);

    const handleAnswerChange = (questionId, value) => {
        setAnswers({
            ...answers,
            [questionId]: value,
        });
    };

    const handleSubmit = async () => {
        if (!interview?.questions?.length) {
            return;
        }

        const unansweredQuestion = interview.questions.find(
            (question) => !answers[question.id]?.trim()
        );

        if (unansweredQuestion) {
            setMessage("Please answer all questions before submitting.");
            return;
        }

        const userId = Number(localStorage.getItem("userId"));

        const requestBody = {
            userId,
            interviewId: Number(interviewId),
            answers: interview.questions.map((question) => ({
                questionId: question.id,
                userAnswer: answers[question.id],
            })),
        };

        try {
            setSubmitting(true);
            setMessage("");

            const response = await API.post("/evaluation", requestBody);
            const result = response.data?.data ?? response.data;

            localStorage.setItem(
                "latestInterviewResult",
                JSON.stringify(result)
            );

            navigate("/result");
        } catch (error) {
            setMessage(
                error.response?.data?.message ||
                "Unable to submit the interview."
            );
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <div className="page-container">
            <div className="page-header">
                <h1>Technical Interview</h1>

                <button
                    className="secondary-button"
                    onClick={() => navigate("/dashboard")}
                >
                    Exit Interview
                </button>
            </div>

            {message && <p className="page-message">{message}</p>}

            {interview && (
                <div className="form-card">
                    <div className="interview-details">
                        <h2>{interview.title}</h2>

                        <p>
                            <strong>Technology:</strong> {interview.technology}
                        </p>

                        <p>
                            <strong>Difficulty:</strong> {interview.difficulty}
                        </p>
                    </div>

                    {interview.questions?.length > 0 ? (
                        <>
                            {interview.questions.map((question, index) => (
                                <div className="question-card" key={question.id}>
                                    <h3>
                                        Question {index + 1}
                                    </h3>

                                    <p>{question.questionText}</p>

                                    <textarea
                                        rows="5"
                                        placeholder="Type your answer here..."
                                        value={answers[question.id] || ""}
                                        onChange={(event) =>
                                            handleAnswerChange(
                                                question.id,
                                                event.target.value
                                            )
                                        }
                                    />
                                </div>
                            ))}

                            <button
                                className="primary-button"
                                onClick={handleSubmit}
                                disabled={submitting}
                            >
                                {submitting
                                    ? "Submitting..."
                                    : "Submit Interview"}
                            </button>
                        </>
                    ) : (
                        <p>No questions are available for this interview.</p>
                    )}
                </div>
            )}
        </div>
    );
}

export default StartInterview;