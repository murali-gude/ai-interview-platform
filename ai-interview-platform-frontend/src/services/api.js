import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080",
});

API.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("token");

        const isLoginRequest =
            config.url === "/users/login" &&
            config.method?.toLowerCase() === "post";

        const isRegisterRequest =
            config.url === "/users" &&
            config.method?.toLowerCase() === "post";

        if (token && !isLoginRequest && !isRegisterRequest) {
            config.headers.Authorization = `Bearer ${token}`;
        }

        return config;
    },
    (error) => Promise.reject(error)
);

API.interceptors.response.use(
    (response) => response,
    (error) => {
        const status = error.response?.status;

        if (status === 401 || status === 403) {
            localStorage.removeItem("token");
            localStorage.removeItem("userId");
            localStorage.removeItem("userName");
            localStorage.removeItem("latestInterviewResult");

            const currentPath = window.location.pathname;

            if (
                currentPath !== "/login" &&
                currentPath !== "/register"
            ) {
                window.location.href = "/login";
            }
        }

        return Promise.reject(error);
    }
);

export default API;