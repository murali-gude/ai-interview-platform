import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function ResultsHistory() {
    const navigate = useNavigate();

    const [results, setResults] = useState([]);
    const [message, setMessage] = useState("Loading results...");

    useEffect(() => {
        const loadResults = async () => {
            const userId = localStorage.getItem("userId");

            if (!userId) {
                navigate("/login");
                return;
            }

            try {
                const response = await API.get(`/results/user/${userId}`);

                const resultData = response.data?.data ?? response.data;

                setResults(Array.isArray(resultData) ? resultData : []);
                setMessage("");
            } catch (error) {
                if (
                    error.response?.status === 401 ||
                    error.response?.status === 403
                ) {
                    localStorage.clear();
                    navigate("/login");
                    return;
                }

                setMessage(
                    error.response?.data?.message ||
                    "Unable to load interview results."
                );
            }
        };

        loadResults();
    }, [navigate]);

    return (
        <div className="page-container">
            <div className="page-header">
                <h1>Interview Results</h1>

                <button
                    className="secondary-button"
                    onClick={() => navigate("/dashboard")}
                >
                    Back to Dashboard
                </button>
            </div>

            {message && <p className="page-message">{message}</p>}

            {!message && results.length === 0 && (
                <div className="form-card">
                    <h2>No interview results found</h2>
                    <p>Complete an interview to view your result history.</p>

                    <button
                        className="primary-button"
                        onClick={() => navigate("/create-interview")}
                    >
                        Start Interview
                    </button>
                </div>
            )}

            {results.length > 0 && (
                <div className="results-list">
                    {results.map((result, index) => {
                        const percentage = Number(result.percentage || 0);

                        const status =
                            result.status ||
                            result.resultStatus ||
                            (percentage >= 60 ? "PASS" : "FAIL");

                        return (
                            <div className="history-card" key={result.id || index}>
                                <div className="history-card-header">
                                    <h3>Interview Result #{result.id || index + 1}</h3>

                                    <span
                                        className={
                                            status === "PASS"
                                                ? "history-status result-pass"
                                                : "history-status result-fail"
                                        }
                                    >
                    {status}
                  </span>
                                </div>

                                <div className="history-details">
                                    <p>
                                        <strong>Score:</strong> {percentage.toFixed(0)}%
                                    </p>

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
                            </div>
                        );
                    })}
                </div>
            )}
        </div>
    );
}

export default ResultsHistory;