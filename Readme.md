# FRD Project
## Table of Contents
- [Overview](#overview)
- [Project Architecture](#project-architecture)
- [Features](#features)
- [Microservices](#microservices)
    - [User Management Microservice](#user-management-microservice)
    - [Product Management Microservice](#product-management-microservice)
    - [Order Processing Microservice](#order-processing-microservice)
    - [API Gateway](#api-gateway)
    - [Service Discovery](#service-discovery)
    - [Spring Boot Admin Server](#spring-boot-admin-server)
    - [Caching and Messaging](#caching-and-messaging)
- [Installation](#installation)
- [Usage](#usage)
- [Docker Setup](#docker-setup)
    - [Step 1: Dockerfile for Each Microservice](#step-1-dockerfile-for-each-microservice)
    - [Step 2: Create docker-compose.yml](#step-2-create-docker-composeyml)
    - [Step 3: Build and Run the Docker Containers](#step-3-build-and-run-the-docker-containers)
- [Documentation and Testing](#documentation-and-testing)
- [Deployment and Maintenance](#deployment-and-maintenance)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)
## Overview
FRD (Full-Stack Reactive Distributed) is a scalable microservices platform developed as part of an internship project. It uses Spring Boot and Spring Cloud components to build a robust and scalable architecture with a focus on scalability, security, and maintainability.
## Project Architecture
The project is structured into multiple microservices, each responsible for specific business functionalities. These microservices communicate with each other through REST APIs and are managed and monitored through various Spring Boot components.
## Features
- **ApiGateway**: Centralized API gateway for routing and security.
- **ConfigServer**: Manages configuration across all microservices.
- **ServiceRegistry**: Service discovery using Eureka.
- **OrderService**: Handles order-related operations.
- **ProductService**: Manages product data and inventory.
- **UserService**: Manages user data and authentication.
- **Spring Admin Server**: Monitoring and management of the microservices.
## Microservices
### User Management Microservice
**Functional Requirements:**
- User registration
- User login
- Profile management
  **Security:**
- JWT tokens for authentication and authorization
  **Entity Class Diagram:**
```plaintext
+----------------+
|      User      |
+----------------+
| - id: Long     |
| - username: String |
| - password: String |
| - email: String |
| - profileDetails: ProfileDetails |
+----------------+
```
### Product Management Microservice
**Functional Requirements:**
- CRUD operations for products
  **Validation:**
- Product attribute validation
  **Entity Class Diagram:**
```plaintext
+----------------+
|    Product     |
+----------------+
| - id: Long     |
| - name: String |
| - description: String |
| - price: Double |
| - stockQuantity: Integer |
+----------------+
```
### Order Processing Microservice
**Functional Requirements:**
- Create, update, cancel orders
- Calculate totals
  **Synchronization:**
- Ensure data consistency across the system
  **Entity Class Diagram:**
```plaintext
+----------------+
|     Order      |
+----------------+
| - id: Long     |
| - userId: Long |
| - productId: Long |
| - quantity: Integer |
| - totalPrice: Double |
| - orderDate: Date |
+----------------+
```
### API Gateway
**Functional Requirements:**
- Request routing
- Authentication checks
  **Features:**
- Rate limiting
- Request logging
### Service Discovery
**Functional Requirements:**
- Eureka server/client registration and discovery
  **Reliability:**
- High availability
- Fault tolerance
### Spring Boot Admin Server
**Functional Requirements:**
- Application monitoring and management
  **Features:**
- Health metrics
- Configurations
- Alerts
### Caching and Messaging
**Functional Requirements:**
- Redis for caching
- Kafka for messaging
  **Reliability:**
- Ensure data consistency and reliability
## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/anj20/FRD.git
   ```
2. Navigate to each service directory and build the project using Maven:
   ```bash
   cd <ServiceDirectory>
   mvn clean install
   ```
## Usage
1. Start the ConfigServer:
   ```bash
   cd ConfigServer
   mvn spring-boot:run
   ```
2. Start the ServiceRegistry:
   ```bash
   cd ServiceRegistry
   mvn spring-boot:run
   ```
3. Start each service (ApiGateway, OrderService, ProductService, UserService):
   ```bash
   cd <ServiceDirectory>
   mvn spring-boot:run
   ```
## Docker Setup
### Step 1: Dockerfile for Each Microservice
Create a `Dockerfile` in the root directory of each microservice.
#### Example Dockerfile for User Management Microservice
```dockerfile
# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim
# Set the working directory inside the container
WORKDIR /app
# Copy the current directory contents into the container at /app
COPY target/user-management.jar /app/user-management.jar
# Expose the port the app runs on
EXPOSE 8081
# Run the jar file
ENTRYPOINT ["java", "-jar", "user-management.jar"]
```
#### Example Dockerfile for Product Management Microservice
```dockerfile
# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim
# Set the working directory inside the container
WORKDIR /app
# Copy the current directory contents into the container at /app
COPY target/product-management.jar /app/product-management.jar
# Expose the port the app runs on
EXPOSE 8082
# Run the jar file
ENTRYPOINT ["java", "-jar", "product-management.jar"]
```
#### Example Dockerfile for Order Processing Microservice
```dockerfile
# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim
# Set the working directory inside the container
WORKDIR /app
# Copy the current directory contents into the container at /app
COPY target/order-processing.jar /app/order-processing.jar
# Expose the port the app runs on
EXPOSE 8083
# Run the jar file
ENTRYPOINT ["java", "-jar", "order-processing.jar"]
```
### Step 2: Create docker-compose.yml
Create a `docker-compose.yml` file in the root directory of your project to orchestrate the microservices.
```yaml
version: '3.8'
services:
  eureka-server:
    image: eureka-server
    build:
      context: ./eureka-server
      dockerfile: Dockerfile
    ports:
      - "8761:8761"
  api-gateway:
    image: api-gateway
    build:
      context: ./api-gateway
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    depends_on:
      - eureka-server
  user-management:
    image: user-management
    build:
      context: ./user-management
      dockerfile: Dockerfile
    ports:
      - "8081:8081"
    depends_on:
      - eureka-server
  product-management:
    image: product-management
    build:
      context: ./product-management
      dockerfile: Dockerfile
    ports:
      - "8082:8082"
    depends_on:
      - eureka-server
  order-processing:
    image: order-processing
    build:
      context: ./order-processing
      dockerfile: Dockerfile
    ports:
      - "8083:8083"
    depends_on:
      - eureka-server
  redis:
    image: "redis:alpine"
    ports:
      - "6379:6379"
  kafka:
    image: wurstmeister/kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    depends_on:
      - zookeeper
  zookeeper:
    image: wurstmeister/zookeeper
    ports:
      - "2181:2181"
```
### Step 3: Build and Run the Docker Containers
1. **Build Docker Images:**
   Navigate to the root directory of your project where the `docker-compose.yml` file is located and run:
   ```bash
   docker-compose build
   ```
2. **Run Docker Containers:**
   After building the images, run the containers using:
   ```bash
   docker-compose up
   ```
This setup includes the core microservices (User Management, Product Management, Order Processing), the Eureka Server for service discovery, the API Gateway, Redis for caching, and Kafka and Zookeeper for messaging.
Each microservice will have its own `Dockerfile`, and `docker-compose.yml` will handle the orchestration. The microservices will register with the Eureka Server and communicate through the API Gateway.
Ensure that each microservice has its own `Dockerfile` and `docker-compose.yml` correctly references each service context. Adjust the paths and configurations according to your project structure.
## Documentation and Testing
**API Documentation:**
- Using Swagger for comprehensive API documentation.
  **Comprehensive Docs:**
- Detailed documentation covering architecture