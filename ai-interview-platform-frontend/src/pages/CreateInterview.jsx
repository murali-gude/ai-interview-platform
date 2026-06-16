import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function CreateInterview() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        title: "",
        technology: "Java",
        difficulty: "EASY",
        numberOfQuestions: 1,
    });

    const [createdInterview, setCreatedInterview] = useState(null);
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleChange = (event) => {
        setFormData({
            ...formData,
            [event.target.name]: event.target.value,
        });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        setLoading(true);
        setError("");
        setCreatedInterview(null);

        const interviewData = {
            ...formData,
            numberOfQuestions: Number(formData.numberOfQuestions),
        };

        try {
            const response = await API.post("/interviews", interviewData);

            const interview = response.data?.data ?? response.data;

            setCreatedInterview(interview);
        } catch (error) {
            setError(
                error.response?.data?.message ||
                "Unable to create interview. Please try again."
            );
        } finally {
            setLoading(false);
        }
    };

    const handleBeginInterview = () => {
        navigate(`/interview/${createdInterview.id}`);
    };

    return (
        <div className="page-container">
            <div className="page-header">
                <h1>Create Interview</h1>

                <button
                    className="secondary-button"
                    onClick={() => navigate("/dashboard")}
                >
                    Back to Dashboard
                </button>
            </div>

            <div className="form-card">
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Interview Title</label>

                        <input
                            type="text"
                            name="title"
                            placeholder="Example: Java Practice Interview"
                            value={formData.title}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="form-group">
                        <label>Technology</label>

                        <select
                            name="technology"
                            value={formData.technology}
                            onChange={handleChange}
                        >
                            <option value="Java">Java</option>
                            <option value="Spring Boot">Spring Boot</option>
                            <option value="React">React</option>
                            <option value="SQL">SQL</option>
                        </select>
                    </div>

                    <div className="form-group">
                        <label>Difficulty</label>

                        <select
                            name="difficulty"
                            value={formData.difficulty}
                            onChange={handleChange}
                        >
                            <option value="EASY">Easy</option>
                            <option value="MEDIUM">Medium</option>
                            <option value="HARD">Hard</option>
                        </select>
                    </div>

                    <div className="form-group">
                        <label>Number of Questions</label>

                        <input
                            type="number"
                            name="numberOfQuestions"
                            min="1"
                            max="20"
                            value={formData.numberOfQuestions}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <button
                        className="primary-button"
                        type="submit"
                        disabled={loading}
                    >
                        {loading ? "Creating..." : "Create Interview"}
                    </button>
                </form>

                {error && <p className="error">{error}</p>}

                {createdInterview && (
                    <div className="success-card">
                        <h3>Interview Created Successfully</h3>

                        <p>
                            <strong>ID:</strong> {createdInterview.id}
                        </p>

                        <p>
                            <strong>Title:</strong> {createdInterview.title}
                        </p>

                        <p>
                            <strong>Technology:</strong>{" "}
                            {createdInterview.technology}
                        </p>

                        <p>
                            <strong>Difficulty:</strong>{" "}
                            {createdInterview.difficulty}
                        </p>

                        <button
                            className="primary-button"
                            onClick={handleBeginInterview}
                        >
                            Begin Interview
                        </button>
                    </div>
                )}
            </div>
        </div>
    );
}

export default CreateInterview;