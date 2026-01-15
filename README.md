![Build](https://github.com/yashwanth14m/expense-manager/actions/workflows/maven.yml/badge.svg)
# Smart Expense & Budget Manager — Backend

A production-grade backend application for tracking expenses, managing monthly budgets, and generating expense analytics.  
Built with **Java 17, Spring Boot, MySQL**, and designed using clean architecture principles.

---

## 🚀 Features

- 🔐 JWT-based authentication & authorization
- 👥 Role-based access control (USER)
- 💸 Expense management (CRUD)
- 📄 Pagination & sorting using Spring Data `Pageable`
- 📊 Monthly expense analytics
  - Total spending
  - Category-wise breakdown
- 💰 Monthly budget management
- ✅ Input validation & global exception handling
- 🧪 Unit tests using JUnit 5 & Mockito
- ⚡ Optimized database queries with indexes

---

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security (JWT)**
- **Spring Data JPA / Hibernate**
- **MySQL**
- **Maven**
- **JUnit 5 & Mockito**

---

## 🏗 Architecture

Controller → Service → Repository
↓
DTOs

yaml
Copy code

- Controllers handle HTTP requests
- Services contain business logic
- Repositories interact with the database
- DTOs prevent entity exposure
- Security handled via JWT filters and method-level authorization

---

## 📦 Project Structure

src/main/java/com/yashwanth/expensemanager
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
└── util

yaml
Copy code

---

## 🔑 Authentication Flow

1. User logs in using email & password
2. Backend generates a JWT token
3. Token is sent in `Authorization` header for secured APIs
4. Spring Security validates token and roles

---

## 📌 API Endpoints (Sample)

### Authentication
POST /api/auth/login

pgsql
Copy code
```json
{
  "email": "user@example.com",
  "password": "password"
}
Create Expense
bash
Copy code
POST /api/expenses
Authorization: Bearer <JWT>
json
Copy code
{
  "description": "Lunch",
  "amount": 250,
  "date": "2026-01-15",
  "categoryId": 1
}
Get Expenses (Paginated)
sql
Copy code
GET /api/expenses?page=0&size=5&sort=date,desc
Authorization: Bearer <JWT>
Create Monthly Budget
bash
Copy code
POST /api/budgets
Authorization: Bearer <JWT>
json
Copy code
{
  "month": "2026-01",
  "amount": 20000
}
Monthly Analytics
sql
Copy code
GET /api/analytics/month/2026-01
Authorization: Bearer <JWT>
json
Copy code
{
  "totalSpent": 5000,
  "categoryBreakdown": [
    { "category": "Food", "amount": 3000 },
    { "category": "Travel", "amount": 2000 }
  ]
}
🗄 Database Optimization
Indexes added for performance:

sql
Copy code
CREATE INDEX idx_expenses_user_date ON expenses (user_id, date);
CREATE INDEX idx_expenses_category ON expenses (category_id);
Ensures fast analytics even with large datasets.

🧪 Testing
Unit tests written for service layer

Mockito used to mock repositories & security context

No Spring context loading for faster tests

Run tests:

bash
Copy code
./mvnw test
▶️ How to Run Locally
Prerequisites
Java 17

MySQL

Maven (or use Maven Wrapper)

Steps
bash
Copy code
./mvnw clean install
./mvnw spring-boot:run
Server starts at:

arduino
Copy code
http://localhost:8080
📈 Project Status
✅ Backend v1 complete
🔜 Frontend integration (planned)
🔜 Docker & CI/CD (optional enhancements)

🧠 What This Project Demonstrates
Real-world backend system design

Secure API development

Clean architecture & best practices

Performance-aware database design

Testing and maintainability focus

👤 Author
Yashwanth Maddisetty
GitHub: https://github.com/yashwanth14m

yaml
Copy code

---

## ✅ WHAT TO DO NOW

1. Open `README.md`
2. **Delete everything**
3. Paste the content above
4. Save
5. Commit:

```bash
git add README.md
git commit -m "Add final professional README"
git push
