# Food Delivery Order Service 🍔

This is a Spring Boot REST API for placing and tracking food orders.
----------------------------------------------------------------------

## ✅ Features

- Create, update, and fetch food orders
- Update order status
- Simulated queue processing
- Global exception handling
- In-memory queue simulation using Java's `ScheduledExecutorService`

--------------------------------

## 🏗️ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

---

## 🚀 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/food-order-service.git
cd food-order-service

## 📘 API Endpoints

### 1. 🛍️ Create Order

- **URL**: `http://localhost:8080/orders/11`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "customerName": "John Doe",
    "items": ["Pizza", "Coke"],
    "address": "123 Street, Delhi",
    "totalAmount": 499.00
  }

### 2. 🛍️ Create Order
URL: /api/orders?page=0&size=5
Method: GET

Success Response:
