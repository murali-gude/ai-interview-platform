
import { Navigate, Route, Routes } from "react-router-dom";

import ProtectedRoute from "./components/ProtectedRoute";
import CreateInterview from "./pages/CreateInterview";
import Dashboard from "./pages/Dashboard";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Result from "./pages/Result";
import ResultsHistory from "./pages/ResultsHistory";
import StartInterview from "./pages/StartInterview";

function App() {
  return (
    <Routes>
      {/* Public routes */}
      <Route path="/" element={<Navigate to="/login" replace />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />

      {/* Protected routes */}
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

      {/* Invalid URL */}
      <Route path="*" element={<Navigate to="/login" replace />} />
    </Routes>
  );
}

export default App;
