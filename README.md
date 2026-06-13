# AI Interview Preparation Platform

A Spring Boot backend application for managing users, interviews, questions, answer evaluation, and interview results.

---

## Features

- User Management APIs
- Interview Management APIs
- Question Management APIs
- Start Interview Workflow
- Answer Submission and Evaluation
- Score and Percentage Calculation
- PASS / FAIL Result Generation
- MySQL Database Integration
- DTO-Based API Responses
- Global Exception Handling
- Request Validation
- Swagger API Documentation

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Swagger / OpenAPI
- Postman
- Git & GitHub

---

## Project Structure

```text
controller
service
repository
entity
dto
exception
config
```

---

## API Endpoints

### User APIs

```http
POST   /users
GET    /users
GET    /users/{id}
PUT    /users/{id}
DELETE /users/{id}
```

### Interview APIs

```http
POST /interviews
GET  /interviews
GET  /interviews/start/{id}
```

### Question APIs

```http
POST /questions
GET  /questions
POST /questions/interview/{interviewId}
GET  /questions/interview/{interviewId}
```

### Evaluation APIs

```http
POST /evaluation
```

### Result APIs

```http
POST /results/user/{userId}/interview/{interviewId}
GET  /results
GET  /results/user/{userId}
```

---

## Database

This project uses MySQL.

Database Name:

```text
ai_interview_platform
```

Database configuration is available in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ai_interview_platform
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

---

## Swagger Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI Docs:

```text
http://localhost:8080/v3/api-docs
```

---

## Validation

Implemented using:

```text
spring-boot-starter-validation
```

Validations include:

- Required Name
- Required Email
- Valid Email Format
- Required Password

---

## Exception Handling

Global exception handling implemented using:

```text
@RestControllerAdvice
```

Handles:

- Resource Not Found
- Validation Errors
- Custom API Error Responses

---

## API Response Format

Success Response:

```json
{
  "success": true,
  "message": "User created successfully",
  "data": {}
}
```

Error Response:

```json
{
  "success": false,
  "message": "Name is required",
  "data": null
}
```

---

## How to Run

### Clone Repository

```bash
git clone https://github.com/murali-gude/ai-interview-platform.git
```

### Open Project

Open in IntelliJ IDEA.

### Configure Database

Create MySQL database:

```sql
CREATE DATABASE ai_interview_platform;
```

### Run Application

Run:

```text
AiInterviewPlatformBackendApplication
```

Application starts at:

```text
http://localhost:8080
```

---

## Testing

Tools Used:

- Postman
- Swagger UI

---

## Current Status

### Completed

- User Management
- Interview Management
- Question Management
- Interview Start API
- Evaluation Engine
- Result Generation
- Validation
- Exception Handling
- Swagger Documentation
- MySQL Integration

### In Progress

- Security
- Authentication
- JWT
- Frontend Development

---

## Author

Murali Krishna Gude

Java Full Stack Developer

GitHub:
https://github.com/murali-gude