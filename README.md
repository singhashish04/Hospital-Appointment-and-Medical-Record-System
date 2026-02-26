# 🏥 Hospital Appointment & Medical Record Management System

## 📌 Project Overview
A backend application built using Spring Boot to manage hospital workflows including patients, doctors, appointments, medical records, and prescriptions. The system enforces real-world business rules and follows a layered architecture design.

---

## 🚀 Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL / MySQL
- REST APIs
- Maven
- Postman

---

## 🏗 Architecture
The project follows layered architecture:

Controller → Service → DAO → Repository

- Controller: Handles HTTP requests and responses
- Service: Contains business logic
- DAO: Handles database interaction logic
- Repository: Extends JPA repository for database operations

---

## 🧩 Core Features

### 👨‍⚕️ Doctor Management
- Add, update, delete doctors
- Store multiple available days using `@ElementCollection`
- Assign doctors to departments

### 👤 Patient Management
- Register and manage patients
- Fetch patient details by ID or phone number

### 📅 Appointment Management
- Book appointments based on doctor availability
- Appointment lifecycle management:
  - BOOKED
  - COMPLETED
  - CANCELED
- Validation to restrict invalid status transitions

### 📋 Medical Records
- Medical records can only be created after appointment completion
- Linked to both doctor and patient

### 💊 Prescription Management
- Prescription can only be created if medical record exists
- One-to-One relationship between MedicalRecord and Prescription

---

## 🛡 Exception Handling
- Custom exception classes
- Centralized exception handling using `@RestControllerAdvice`
- Standardized API response structure

---

## 🗄 Database Relationships
- One-to-Many (Doctor → Appointment)
- One-to-Many (Patient → Appointment)
- One-to-One (MedicalRecord → Prescription)
- Many-to-One (Doctor → Department)
- ElementCollection (Doctor → Available Days)

---

## 🧠 Business Logic Highlights
- Appointment status validation
- Conditional medical record creation
- Conditional prescription creation
- Data integrity enforcement

---

## 📌 Key Learnings
- Designing layered backend architecture
- Implementing JPA relationships
- Handling complex business logic
- Managing entity relationships and preventing circular JSON
- Implementing global exception handling

---

## 📷 API Testing
All APIs were tested using Postman with structured JSON request/response format.

---

## 📦 Future Improvements
- Add authentication & role-based authorization
- Implement pagination & sorting
- Add Swagger documentation
- Deploy on cloud platform (AWS / Render)

---

## 👨‍💻 Author
Ashish Kumar Singh  
Java Full Stack Developer
