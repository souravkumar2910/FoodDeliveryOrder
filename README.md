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
    "totalAmount": 499.00
  }

### 2. 🛍️Get All Orders (By Pagination)
- **URL**: `http://localhost:8080/orders?page=0&size=3`
- **Method**: `GET`
- **Success Response:**:
  ```json
{
 "content":[
      {"orderId": 1, "customerName": "John Doe", "items":["Pizza", "Coke",…},
      {"orderId": 2, "customerName": "Elevish", "items":["Pizza", "Coke"…},
      {"orderId": 3, "customerName": "Ravi", "items":["Pizza", "Coke"…}
  ],
    "page":{
    "size": 3,
    "number": 0,
    "totalElements": 11,
    "totalPages": 4
  }
}

### 3. 🛍️ Order Status change
- **URL**: `http://localhost:8080/orders/4/status`
- **Method**: `PUT`
- **Request Body:**:
  ```json
{
  "status": "CANCELLED"
}
- **Success Response:**:
{
"orderId": 4,
"status": "Order has been CANCELLED"
}

### 4. 🛍️Get Order (By ID)
- **URL**: `http://localhost:8080/orders/11`
- **Method**: `GET`
- **Success Response:**:
  ```json
{
"id": 11,
"customerName": "Deepak",
"items":[
"Burger",
"Fries",
"Coke"
],
"totalAmount": 350.75,
"orderTime": "2025-07-21T16:50:42.033901",
"status": "processed"
}

##- Simulated queue processing
- # I have used LinkedBlockingQueue to simulate asynchronous order processing.
- # When a POST request is made to create a new order, the order is added to the queue and its status is initially set to "PROCESSING".
- # A background thread continuously polls the queue. After a delay of 10 seconds, the order status is automatically updated to "PROCESSED".

## Global exception handling
I uses a centralized exception handling mechanism implemented using Spring’s @RestControllerAdvice. The GlobalExceptionHandler class is responsible for catching and returning consistent error responses for various types of exceptions.

Handled Exceptions:

MethodArgumentNotValidException – Triggered when validation on an argument annotated with @Valid fails. Returns detailed validation messages for each invalid field.

ConstraintViolationException – Handles constraint violations in request parameters or path variables.

HttpMessageNotReadableException – Catches malformed or unreadable JSON payloads.

OrderNotFoundException – Custom exception to handle scenarios where an order ID is not found.

HttpRequestMethodNotSupportedException – Triggered when an unsupported HTTP method is used.

Exception – Fallback handler for all uncaught exceptions to prevent server crashes.

# Example:
### 1. 🛍️ Trying to get Orber by ID which is not present
- **URL**: `http://localhost:8080/orders/12`
- **Method**: `GET`
- **Error Response**:
  ```json
{
"timestamp": "2025-07-22T11:19:46.8562388",
"status": 404,
"error": "Not Found",
"message": "Order not found with id:12",
"path": "/orders/12"
}

### 2. 🛍️ Get Order By ID
- **URL**: `http://localhost:8080/orders/12`
- **Method**: `POST`
- **Error Response**:
  ```json
{
"timestamp": "2025-07-22T11:24:05.8299027",
"status": 405,
"error": "Method Not Allowed",
"message": "HTTP method not supported. Supported: [GET]",
"path": "/orders/11"
}

### 3. 🛍️ Get Order By ID
- **URL**: `http://localhost:8080/orders/12`
- **Method**: `POST`
- **Request Body:**:
  ```json
 {
    "customerName": "",
    "items": ["Pizza", "Coke"],
    "totalAmount": 0
  }
- **Error Response**:
  ```json
{
"timestamp": "2025-07-22T11:30:17.7170569",
"status": 400,
"error": "Validation Failed",
"message": "{totalAmount=Amount must be at least 1, customerName=Customer name is required}",
"path": "/orders"
}

