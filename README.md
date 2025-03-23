# 🛒 Store Management API

This is a Java Spring Boot application for managing store products and authenticating users with role-based access control using **JWT tokens**.

---

## 🚀 Features

- ✅ Add, update, delete products (Admin only)
- ✅ View products (Admin and User)
- ✅ Change price/quantity of a product (Admin only)
- ✅ Basic authentication via JWT
- ✅ Role-based endpoint security
- ✅ In-memory H2 database for quick development/testing
- ✅ Clean architecture with controllers, services, repositories, DTOs, and mappers
- ✅ Unit tests
- ✅ Integration tests with `MockMvc`

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot
- Spring Security
- H2 Database (in-memory)
- JWT (for authentication & authorization)
- Maven (build tool)
- Junit5

---

## 🧪 How It Works

### ✅ Authentication Flow

On application startup we are inserting 2 mock users which would serve as already register users(admin and user)
1. A user logs in with `/api/auth/login` using a `username` and `password`.
2. If credentials are valid, the API returns a **JWT token**.
3. The client uses this token as a **Bearer** token in the `Authorization` header for protected endpoints.
4. The **JWT token** expires in 15 minutes so the token which will be give as examples below are just as examples.

---

### 🔒 Role-Based Access

| Endpoint                    | Access         | Method |
|----------------------------|----------------|--------|
| `/products` (POST)         | Admin only     | `POST` |
| `/products` (GET)          | Admin, User    | `GET`  |
| `/products/{id}`           | Admin, User    | `GET`  |
| `/products/{id}/price`     | Admin only     | `PATCH` |
| `/products/{id}/quantity`  | Admin only     | `PATCH` |
| `/products/{id}`           | Admin only     | `DELETE` |
| `/api/auth/login`          | Public         | `POST` |

---

## 📦 Sample Payloads

### 🔐 Login

**POST** `/api/auth/login`

curl --location 'http://localhost:8081/api/auth/login' \
--header 'Content-Type: application/json' \
--data '{
"username": "admin",
"password": "admin123"
}'

### 🔐 Product management

**GET** `/products`

curl --location 'http://localhost:8081/products' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwidXNlcm5hbWUiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0MjY3MzY5MiwiZXhwIjoxNzQyNjc0NTkyfQ.3QKz0UupEU0CY-gayNXm1SJ9_1E5SX9q1lKL85A_AFs' \

**GET** `/products/{id}`

curl --location 'http://localhost:8081/products/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwidXNlcm5hbWUiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0MjY3MzY5MiwiZXhwIjoxNzQyNjc0NTkyfQ.3QKz0UupEU0CY-gayNXm1SJ9_1E5SX9q1lKL85A_AFs' \

**POST** `/products`

curl --location 'http://localhost:8081/products' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwidXNlcm5hbWUiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0MjY3MzY5MiwiZXhwIjoxNzQyNjc0NTkyfQ.3QKz0UupEU0CY-gayNXm1SJ9_1E5SX9q1lKL85A_AFs' \
--header 'Content-Type: application/json' \
--data '    {"name" : "Iphone",
"price" : "1400.00",
"quantity" : 2
}'

**PATCH** `/products/{id}/price?price={newPrice}`

curl --location --request PATCH 'http://localhost:8081/products/1/price?price=1800' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwidXNlcm5hbWUiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0MjY1NzQyMiwiZXhwIjoxNzQyNjU4MzIyfQ.bPco195MSMbXDO0qah7HLRX6JrW4MH1gyZo8ZK3dYsE' \

**PATCH** `/products/{id}/quantity?quantity={newQuantity}`

curl --location --request PATCH 'http://localhost:8081/products/1/quantity?quantity=5' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwidXNlcm5hbWUiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0MjY3MzY5MiwiZXhwIjoxNzQyNjc0NTkyfQ.3QKz0UupEU0CY-gayNXm1SJ9_1E5SX9q1lKL85A_AFs' \

**DELETE** `/products/{id}`

curl --location --request DELETE 'http://localhost:8081/products/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwidXNlcm5hbWUiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc0MjY3MzY5MiwiZXhwIjoxNzQyNjc0NTkyfQ.3QKz0UupEU0CY-gayNXm1SJ9_1E5SX9q1lKL85A_AFs' \
