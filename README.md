# AI-Powered Interview Preparation Platform

## Overview

AI-Powered Interview Preparation Platform is a Spring Boot based application that helps users prepare for technical interviews by creating interview sessions, answering questions, and receiving evaluation results.

## Features

### User Management

* User Registration
* User Login
* JWT Authentication

### Question Management

* Create Questions
* Update Questions
* Delete Questions
* Get Questions by ID
* Get All Questions

### Interview Management

* Create Interview
* Start Interview
* Fetch Technology-Specific Questions

### Evaluation & Results

* Submit Answers
* Evaluate Responses
* Calculate Score
* Pass/Fail Decision
* Store Interview Results
* View User Results

## Technology Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* JWT Authentication

### Database

* MySQL

### Tools

* Maven
* Postman
* Git
* GitHub

## API Endpoints

### Authentication

POST /users/register
POST /users/login

### Questions

GET /questions
GET /questions/{id}
POST /questions
PUT /questions/{id}
DELETE /questions/{id}

### Interviews

GET /interviews
GET /interviews/{id}
POST /interviews
POST /interviews/{id}/start
DELETE /interviews/{id}

### Evaluation

POST /evaluation

### Results

GET /results
GET /results/user/{userId}

## Project Status

Backend Development Completed Successfully.

Future Enhancements:

* React Frontend
* AI-Based Answer Evaluation
* Interview Analytics Dashboard
* Voice-Based Mock Interviews
