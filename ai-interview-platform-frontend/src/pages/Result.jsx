import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function Result() {
    const navigate = useNavigate();
    const [result, setResult] = useState(null);

useEffect(() => {
    const savedResult = localStorage.getItem("latestInterviewResult");

    if (savedResult) {
        setResult(JSON.parse(savedResult));
    }
}, []);

if (!result) {
    return (
        <div className="page-container">
            <div className="form-card result-card">
                <h2>No result available</h2>
                <p>Please complete an interview first.</p>

                <button
                    className="primary-button"
                    onClick={() => navigate("/dashboard")}
                >
                    Go to Dashboard
                </button>
            </div>
        </div>
    );
}

const percentage = Number(result.percentage || 0);

const status =
    result.status ||
    result.resultStatus ||
    (percentage >= 60 ? "PASS" : "FAIL");

const passed = status === "PASS";

return (
    <div className="page-container">
        <div className="page-header">
            <h1>Interview Result</h1>

            <button
                className="secondary-button"
                onClick={() => navigate("/dashboard")}
            >
                Back to Dashboard
            </button>
        </div>

        <div className="form-card result-card">

            <div
                className={
                    passed
                        ? "result-status result-pass"
                        : "result-status result-fail"
                }
            >
                {status}
            </div>

            <h2>Your Score</h2>

            <div className="score-circle">
                {percentage.toFixed(0)}%
            </div>

            <div className="result-details">
                <p>
                    <strong>Total Questions:</strong>{" "}
                    {result.totalQuestions}
                </p>

                <p>
                    <strong>Correct Answers:</strong>{" "}
                    {result.correctAnswers}
                </p>

                <p>
                    <strong>Incorrect Answers:</strong>{" "}
                    {Number(result.totalQuestions) -
                        Number(result.correctAnswers)}
                </p>
            </div>

            {result.feedback && (
                <div className="result-section">
                    <h3>Feedback</h3>
                    <p>{result.feedback}</p>
                </div>
            )}

            {result.strengths && (
                <div className="result-section">
                    <h3>Strengths</h3>
                    <p>{result.strengths}</p>
                </div>
            )}

            {result.weaknesses && (
                <div className="result-section">
                    <h3>Areas for Improvement</h3>
                    <p>{result.weaknesses}</p>
                </div>
            )}

            {result.improvedAnswer && (
                <div className="result-section">
                    <h3>Improved Answer</h3>
                    <p>{result.improvedAnswer}</p>
                </div>
            )}

            <div className="result-actions">
                <button
                    className="primary-button"
                    onClick={() => navigate("/create-interview")}
                >
                    Take Another Interview
                </button>

                <button
                    className="secondary-button"
                    onClick={() => navigate("/dashboard")}
                >
                    Dashboard
                </button>
            </div>

        </div>
    </div>
);

}

export default Result;
