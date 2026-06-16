import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import API from "../services/api";

function Login() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        email: "",
        password: "",
    });

    const [message, setMessage] = useState("");

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await API.post("/users/login", formData);

            const token = response.data.data.token;
            const user = response.data.data.user;

            localStorage.setItem("token", token);
            localStorage.setItem("userId", user.id);
            localStorage.setItem("userName", user.name);

            navigate("/dashboard");
        } catch (error) {
            setMessage("Invalid email or password");
        }
    };

    return (
        <div className="auth-container">
            <div className="auth-card">
                <h2>AI Interview Platform</h2>
                <p>Login to continue</p>

                <form onSubmit={handleLogin}>
                    <input
                        type="email"
                        name="email"
                        placeholder="Email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="password"
                        name="password"
                        placeholder="Password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />

                    <button type="submit">Login</button>
                </form>

                {message && <p className="error">{message}</p>}

                <p>
                    New user? <Link to="/register">Register</Link>
                </p>
            </div>
        </div>
    );
}

export default Login;