# Real-Time Chat Application (Backend)

A backend for a real-time chat application built with Spring Boot. This project is being developed to gain a deeper understanding of backend development by implementing authentication, database management, and real-time communication from scratch rather than relying solely on tutorials.

The project is currently under active development, with new features being added as I continue learning modern backend technologies.

---

## Features

### Authentication
- User Registration
- Login using Username or Email
- JWT Authentication
- Protected REST APIs

### User Management
- Search users by username

### Messaging
- Real-time messaging using Spring WebSocket (STOMP)
- Private message delivery
- Message persistence using MySQL

---

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring WebSocket (STOMP)
- Maven

### Database
- MySQL
- Hibernate / JPA

### Authentication
- JWT (JSON Web Token)
- Custom JWT Authentication Filter
- UserDetails
- UserDetailsService

---

## Project Structure

```
src
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── security
└── websocket
```

---

## What I Learned

This project has helped me understand:

- Spring Boot application architecture
- REST API development
- Spring Security
- JWT authentication and authorization
- Security Filter Chain
- UserDetails and UserDetailsService
- SecurityContextHolder
- Spring Data JPA
- Repository-Service-Controller architecture
- MySQL integration
- WebSocket-based real-time communication

Instead of treating Spring Security as a black box, I wanted to understand how every request is authenticated, how JWT is validated, and how Spring Security manages authenticated users internally.

---

## Current Progress

Completed:
- User Registration
- Login API
- JWT Authentication
- Protected Endpoints
- User Search API
- WebSocket Messaging
- Private Messaging

Currently Working On:
- JWT Authentication for WebSocket Connections

Planned Features:
- Online/Offline Status
- Typing Indicator
- Read Receipts
- Group Chats
- File Sharing
- Docker Support
- AWS Deployment

---

## Authentication Flow

```
Client
    │
    ▼
Login Request
    │
    ▼
JWT Generated
    │
    ▼
Authorization: Bearer <token>
    │
    ▼
JWT Authentication Filter
    │
    ▼
Spring Security
    │
    ▼
Protected API
```

---

## Getting Started

### Clone the repository

```bash
git clone https://github.com/your-username/your-repository.git
```

### Configure the database

Update the following properties in `application.properties`:

```properties
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

jwt.secret=YOUR_SECRET_KEY
jwt.expiration=86400000
```

### Run the application

```bash
mvn spring-boot:run
```

---

## Future Roadmap

- Secure WebSocket connections using JWT
- Presence management (Online/Offline)
- Typing indicators
- Read receipts
- Group messaging
- File sharing
- Docker containerization
- Cloud deployment on AWS

---

## About This Project

This project is part of my backend development journey. The goal is not just to build a chat application, but to understand the concepts behind Spring Boot, Spring Security, JWT, WebSockets, and scalable backend architecture by implementing them step by step.

As I continue learning, this repository will evolve with new features, improvements, and best practices.

---

## Author

**Aditya Durgapal**

GitHub: https://github.com/iitzadii

LinkedIn: https://www.linkedin.com/in/adityadurgapal/
