## 💼 Finance Dashboard Backend (Spring Boot)

# 📌 Project Overview

This project is a production-style backend system built using Java and Spring Boot for managing financial records. It allows users to create, view, filter, and analyze income/expense data with secure role-based access.

This project demonstrates real-world backend engineering practices, making it highly suitable for interviews.

---
# 🧰 Tech Stack

# Category	      Technology

Language	      Java 21
Framework	      Spring Boot 3
Database	      MySQL
ORM	            Spring Data JPA (Hibernate)
Security	      Spring Security (Basic Auth)
Build Tool	      Maven
API Testing 	  Postman

---
# 🏗️ Architecture

Controller → DTO → Service → Repository → Database
              ↓
        Exception Handling
              ↓
          Security Layer

In my project, the Controller layer exposes REST APIs and handles incoming HTTP requests.
I use DTOs to transfer data between the Controller and Service layers, ensuring that internal entity structures are not exposed directly.
The Service layer contains the core business logic and processes the data based on the API requirements.
It interacts with the Repository layer, which uses Spring Data JPA to communicate with the database for CRUD operations.
I implemented global exception handling using @RestControllerAdvice to provide consistent error responses across the application.
For security, I used Spring Security with role-based access control, defining roles like ADMIN, ANALYST, and VIEWER. Currently, authentication is handled using Basic Auth with in-memory users, and in a production scenario, I would use encrypted passwords with BCrypt and JWT-based authentication.

---
# 🧠 Breakdown 
# Concept	            Correct Meaning

Controller   	      Handles HTTP requests
DTO	                  Transfers data safely
Service	            Business logic
Repository	            DB interaction
Exception Handling     	Global via @RestControllerAdvice
Security	            Role-based + Basic Auth
Passwords	            Plain ({noop}), not encrypted

---
# Api testing

AUTH (FOR MOST APIs)
👉 Postman → Authorization → Basic Auth

Username: admin
Password: admin123

1. CREATE RECORD  : POST /api/records
body input: 
     {
  "amount": 5000,
  "type": "INCOME",
  "category": "Salary",
  "date": "2026-04-01",
  "notes": "April Salary"
      }
Output: 
{
  "message": "Record created",
  "data": {
    "id": 1,
    "amount": 5000,
    "type": "INCOME",
    "category": "Salary",
    "date": "2026-04-01",
    "notes": "April Salary"
  }
}

2. GET ALL RECORDS  : GET /api/records
 output: 
 [
  {
    "id": 1, "amount": 5000,
    "type": "INCOME",
    "category": "Salary",
    "date": "2026-04-01",
    "notes": "April Salary"
  }
]

3. FILTER BY CATEGORY
✅ Request
GET /api/records/filter?category=Salary
✅ Response
[
  {
    "id": 1,
    "amount": 5000,
    "category": "Salary"
  }
]

4. PAGINATION
✅ Request
GET /api/records/page?page=0&size=5
✅ Response
{
  "content": [...],
  "totalElements": 10,
  "totalPages": 2
}

 5. DASHBOARD SUMMARY
✅ Request
GET /api/records/summary
✅ Response
{
  "income": 5000,
  "expense": 2000,
  "balance": 3000
}

6. DELETE RECORD
✅ Request
DELETE /api/records/1
✅ Response
{
  "message": "Record deleted successfully",
  "data": null
}

# ADMIN

✔ POST → allowed
✔ GET → allowed

# ANALYST

✔ POST → allowed
✔ GET → allowed

# VIEWER

❌ POST → 403
✔ SUMMARY → allowed
---

## 🎯 FINAL ROLE PERMISSIONS

API	   ADMIN	 ANALYST  	VIEWER
POST	  ✅      	✅	       ❌
PUT     ✅	      ✅	       ❌
DELETE	✅	      ❌ (opt)	 ❌
GET all	✅	      ✅        ✅
FILTER	✅      	✅	       ✅
PAGINATION	✅  	✅        ✅
SUMMARY	✅	      ✅      	 ✅
