import { Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import CreateInterview from "./pages/CreateInterview";
import StartInterview from "./pages/StartInterview";
import Result from "./pages/Result";
import ResultsHistory from "./pages/ResultsHistory";

function App() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/login" replace />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/create-interview" element={<CreateInterview />} />
            <Route
                path="/interview/:interviewId"
                element={<StartInterview />}
            />
            <Route path="/result" element={<Result />} />
            <Route path="/results-history" element={<ResultsHistory />} />
        </Routes>
    );
}

export default App;