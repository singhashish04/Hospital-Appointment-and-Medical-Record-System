*🏥 Hospital Appointment & Medical Record Management System*
📌 Project Overview

Hospital Appointment and Medical Record Management System is a backend application built using Spring Boot to manage patients, doctors, appointments, medical records, and prescriptions.

The system enforces real-world hospital workflow with proper validation and business logic.

🚀 Features

Patient registration and management

Doctor management with available days

Appointment booking with lifecycle management (BOOKED → COMPLETED / CANCELED)

Medical record creation only after appointment completion

Prescription creation only if medical record exists

Centralized exception handling

Standardized API response structure

Layered architecture (Controller → Service → DAO → Repository)

🛠 Tech Stack

Java

Spring Boot

Spring Data JPA

Hibernate

PostgreSQL / MySQL

REST APIs

Maven

Postman

🧠 Architecture

The project follows layered architecture:

Controller Layer – Handles HTTP requests and responses

Service Layer – Contains business logic and validations

DAO Layer – Manages database interaction logic

Repository Layer – Uses Spring Data JPA for persistence

🗂 Entity Relationships

One Doctor → Many Appointments

One Patient → Many Appointments

One Appointment → One Medical Record

One Medical Record → One Prescription

Doctor → Department (Many-to-One)

Doctor Available Days stored using @ElementCollection

🔐 Business Logic Rules

Only BOOKED appointment can be updated to COMPLETED or CANCELED

Completed or Canceled appointments cannot be modified

Medical record can only be created after appointment is COMPLETED

Prescription can only be created if medical record exists

⚠ Exception Handling

Custom exceptions for invalid IDs and business rule violations

Global exception handling using @RestControllerAdvice

Standardized API response structure

📦 API Testing

All APIs were tested using Postman with proper JSON request and response handling.

📈 Future Improvements

Add authentication and authorization (Spring Security)

Add pagination and sorting

Add slot-based appointment booking

Deploy on cloud platform
