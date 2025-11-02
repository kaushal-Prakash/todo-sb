# 📝 TodoApp — Spring Boot + JWT Authentication

A simple **Todo Management** backend built with **Spring Boot**, featuring:
- 🔐 JWT-based authentication using [Auth0 Java JWT](https://github.com/auth0/java-jwt)
- 🧂 Password hashing with BCrypt
- ✅ Route protection via a custom JWT middleware
- 🧠 Layered architecture (Controller → Service → Repository → Model)

---

## ⚙️ Tech Stack

- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **MySQL**
- **Auth0 Java JWT**
- **BCryptPasswordEncoder**
- **Jakarta Persistence (JPA)**
- **Maven**

---

## 🏗️ Project Structure

```

src/
└── main/java/com/todo/TodoApp
├── Controllers/        # API endpoints (Auth, Task)
├── Models/             # JPA Entities (User, Task)
├── Repos/              # Spring Data Repositories
├── Services/           # Business Logic
├── JwtFilter.java      # Middleware to verify JWT
└── TodoAppApplication.java  # Main entry point

````

---

## 🚀 Features

### 🧑‍💻 Authentication
- **Signup** (`POST /auth/signup`) — registers new users with hashed passwords.  
- **Login** (`POST /auth/login`) — validates credentials and returns a **JWT** cookie.  

### 🧾 Task Management
- **GET /get-tasks** — Fetch all tasks (JWT required)  
- **POST /add-task** — Add a task (JWT required)  
- **PUT /delete/{id}** — Delete a task  
- **GET /complete/{id}** — Mark a task as completed  

All `/auth/*` routes are **public**, everything else is protected by the JWT middleware.

---

## 🔒 JWT Middleware

Located in [`JwtFilter.java`](src/main/java/com/todo/TodoApp/JwtFilter.java)

- Checks for JWT token in cookies.  
- Verifies it using HMAC256 secret.  
- Rejects unauthorized requests with JSON response:
```json
{ "message": "Invalid or expired token" }
````

---

## 🧂 Password Hashing

Implemented in `AuthService.java` using `BCryptPasswordEncoder`.

* Passwords are **encoded** before saving.
* During login, passwords are **verified** using `.matches()`.

---
### 2️⃣ Configure Database

Edit `src/main/resources/application.yaml` or `application.properties`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/todoapp
    username: root
    password: yourpassword
  jpa:
    hibernate:
      ddl-auto: update
```

### 3️⃣ Install dependencies & run

```bash
mvn spring-boot:run
```

### 4️⃣ Test the API

Use Postman or frontend client:

* Signup → `/auth/signup`
* Login → `/auth/login`
* Fetch Tasks → `/get-tasks`

---

## 🔐 Example JWT Cookie Response

After successful login:

```json
{
  "message": "Login successful"
}
```

The token is also sent as an HTTP-only cookie for secure access.

---

## 🧑‍🏫 Author

**Kaushal Prakash**
Full Stack Developer — Java | MERN | Spring Boot



