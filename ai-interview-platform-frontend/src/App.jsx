import { Routes, Route, Navigate } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import CreateInterview from "./pages/CreateInterview";
import StartInterview from "./pages/StartInterview";
import Result from "./pages/Result";
import ResultsHistory from "./pages/ResultsHistory";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/login" replace />} />

            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />

            <Route
                path="/dashboard"
                element={
                    <ProtectedRoute>
                        <Dashboard />
                    </ProtectedRoute>
                }
            />

            <Route
                path="/create-interview"
                element={
                    <ProtectedRoute>
                        <CreateInterview />
                    </ProtectedRoute>
                }
            />

            <Route
                path="/interview/:interviewId"
                element={
                    <ProtectedRoute>
                        <StartInterview />
                    </ProtectedRoute>
                }
            />

            <Route
                path="/result"
                element={
                    <ProtectedRoute>
                        <Result />
                    </ProtectedRoute>
                }
            />

            <Route
                path="/results-history"
                element={
                    <ProtectedRoute>
                        <ResultsHistory />
                    </ProtectedRoute>
                }
            />

            <Route path="*" element={<Navigate to="/login" replace />} />
        </Routes>
    );
}

export default App;