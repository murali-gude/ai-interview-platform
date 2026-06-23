import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function ResultsHistory() {
    const navigate = useNavigate();


const [results, setResults] = useState([]);
const [message, setMessage] = useState("Loading results...");

useEffect(() => {
    const loadResults = async () => {
        try {
            const userId = localStorage.getItem("userId");

            const response = await API.get(
                `/results/user/${userId}`
            );

            const data =
                response.data?.data ?? response.data;

            setResults(data);
            setMessage("");
        } catch (error) {
            setMessage(
                error.response?.data?.message ||
                "Unable to load results."
            );
        }
    };

    loadResults();
}, []);

return (
    <div className="page-container">
        <div className="page-header">
            <h1>Interview History</h1>

            <button
                className="secondary-button"
                onClick={() => navigate("/dashboard")}
            >
                Dashboard
            </button>
        </div>

        {message && (
            <p className="page-message">{message}</p>
        )}

        {results.length > 0 && (
            <div className="results-grid">
                {results.map((result) => (
                    <div
                        className="result-history-card"
                        key={result.id}
                    >
                        <h3>
                            {result.interview?.title}
                        </h3>

                        <p>
                            <strong>Technology:</strong>{" "}
                            {result.interview?.technology}
                        </p>

                        <p>
                            <strong>Score:</strong>{" "}
                            {result.percentage}%
                        </p>

                        <p>
                            <strong>Status:</strong>{" "}
                            {result.resultStatus}
                        </p>

                        <p>
                            <strong>Correct:</strong>{" "}
                            {result.correctAnswers}/
                            {result.totalQuestions}
                        </p>
                    </div>
                ))}
            </div>
        )}
    </div>
);


}

export default ResultsHistory;
