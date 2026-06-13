# AI Interview Preparation Platform

A Spring Boot backend application for managing users, interviews, questions, answer evaluation, and interview results.

## Features

- User management APIs
- Interview management APIs
- Question management APIs
- Start interview workflow
- Submit answers and evaluate automatically
- Score and percentage calculation
- PASS/FAIL result generation
- MySQL database integration
- DTO-based API responses
- Global exception handling
- Request validation
- Swagger API documentation

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Hibernate
- Maven
- Lombok
- Swagger / OpenAPI
- Postman
- Git & GitHub

## API Endpoints

### Users
- POST `/users`
- GET `/users`
- GET `/users/{id}`
- PUT `/users/{id}`
- DELETE `/users/{id}`

### Interviews
- POST `/interviews`
- GET `/interviews`
- GET `/interviews/start/{id}`

### Questions
- POST `/questions`
- GET `/questions`
- POST `/questions/interview/{interviewId}`
- GET `/questions/interview/{interviewId}`

### Evaluation
- POST `/evaluation`

### Results
- POST `/results/user/{userId}/interview/{interviewId}`
- GET `/results`
- GET `/results/user/{userId}`

## Swagger URL

```text
http://localhost:8080/swagger-ui/index.html