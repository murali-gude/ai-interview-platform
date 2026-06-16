import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function Dashboard() {
    const navigate = useNavigate();

    const [user, setUser] = useState(null);
    const [message, setMessage] = useState("Loading profile...");

    useEffect(() => {
        const fetchUserProfile = async () => {
            try {
                const response = await API.get("/users/me");

                setUser(response.data.data);
                setMessage("");
            } catch (error) {
                localStorage.removeItem("token");
                localStorage.removeItem("userId");
                localStorage.removeItem("userName");

                navigate("/login");
            }
        };

        fetchUserProfile();
    }, [navigate]);

    const handleLogout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("userId");
        localStorage.removeItem("userName");

        navigate("/login");
    };

    return (
        <div className="dashboard-container">
            <nav className="navbar">
                <h2>AI Interview Platform</h2>

                <button className="logout-button" onClick={handleLogout}>
                    Logout
                </button>
            </nav>

            <main className="dashboard-content">
                {message && <p>{message}</p>}

                {user && (
                    <>
                        <section className="welcome-card">
                            <h1>Welcome, {user.name}</h1>
                            <p>{user.email}</p>
                        </section>

                        <section className="dashboard-grid">
                            <div className="dashboard-card">
                                <h3>Start Interview</h3>
                                <p>Practice technical interview questions.</p>

                                <button onClick={() => navigate("/create-interview")}>
                                    Start Interview
                                </button>
                            </div>

                            <div className="dashboard-card">
                                <h3>Interview Results</h3>
                                <p>View your previous scores and results.</p>

                                <button>View Results</button>
                            </div>
                        </section>
                    </>
                )}
            </main>
        </div>
    );
}

export default Dashboard;