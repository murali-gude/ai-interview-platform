import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080",
});

API.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("token");

        const isPublicRequest =
            config.url === "/users/login" ||
            (config.url === "/users" && config.method === "post");

        if (token && !isPublicRequest) {
            config.headers.Authorization = `Bearer ${token}`;
        }

        return config;
    },
    (error) => Promise.reject(error)
);

API.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 401 || error.response?.status === 403) {
            localStorage.removeItem("token");
            localStorage.removeItem("userId");
            localStorage.removeItem("userName");
        }

        return Promise.reject(error);
    }
);

export default API;