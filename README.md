# Api-Java-Calculator

Calculator Service with Kafka Integration

This project is a calculator service that exposes a RESTful API for performing basic arithmetic operations: addition, subtraction, multiplication, and division. The project uses Apache Kafka for communication between the modules and is built using Spring Boot.

## Features

- **RESTful API** that supports basic arithmetic operations:
  - Sum (`/sum`)
  - Subtraction (`/subtraction`)
  - Multiplication (`/multiply`)
  - Division (`/divide`)
- **Kafka-based communication**: The services communicate with each other through Apache Kafka, ensuring decoupling and scalability.
- **Arbitrary precision decimal numbers**: The service supports decimal numbers with high precision using `BigDecimal`.
- **Dockerized environment**: The project includes Docker configurations to build and run the services in isolated containers. Docker Compose is used to manage multiple services.

## Modules

This project consists of two modules:

1. **Calculator Module**: Responsible for handling arithmetic operations.
2. **REST API Module**: Exposes the endpoints to perform operations through RESTful API calls.

## Technologies Used

- **Spring Boot**: The project uses Spring Boot for building the API and integrating with Kafka.
- **Apache Kafka**: A distributed event streaming platform for communication between services.
- **Docker & Docker Compose**: For containerization and orchestration of the services.

## Prerequisites

To run this project, you need the following:

- Java 11 or later
- Maven
- Docker and Docker Compose
- Apache Kafka (configured via Docker)

## Setup and Installation

## 1. Build the Project

### Using Maven:
```bash
mvn clean install
```

## 2. Run Tests

Run tests in all modules.

```bash
mvn test
```

## 2. Run the Application

The project uses Docker to run all the modules and kafka.

```bash
docker-compose up --build
```

## 3. Access the REST API

Once the application is up and running, the RESTful API can be accessed at the following endpoints:

### **POST `/api/calculator/calculate`**

Example request body:

```json
{
  "operation": "sum",
  "a": "10",
  "b": "5"
}
```
#Request Parameters:

### Request Parameters:

- `operation` (String): The operation to perform. Possible values are `add`, `subtract`, `multiply`, `divide`.
- `a` (String): The first operand (can be a decimal number).
- `b` (String): The second operand (can be a decimal number).

### Response example

```json
{
  "message": "Calculation request sent to Kafka: add:5:10"
}
```



