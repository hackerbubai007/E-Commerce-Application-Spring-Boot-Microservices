# E-Commerce-Application-Spring-Boot-Microservices

This project is a scalable and cloud-native E-Commerce backend system developed using Spring Boot Microservices architecture. 
It follows real-world enterprise design patterns such as service discovery, centralized configuration, API gateway, 
event-driven communication using Kafka, and fault tolerance using Resilience4j.

The system is designed with independent, loosely coupled microservices for user management, product catalog, 
inventory management, cart, order processing, payment handling, notifications, and reviews & ratings.

## Microservices

1. User Microservice
   - Manages customer accounts and roles
   - Tables: users, roles
   - APIs: POST /users, GET /users/{id}
   - Database: MySQL

2. Product Microservice
   - Manages products and categories
   - Tables: products, categories
   - APIs: POST /products, GET /products/{id}
   - Database: MySQL

3. Inventory Microservice
   - Handles stock validation and updates
   - Table: inventory
   - APIs: GET /inventory/{productId}, PUT /inventory/reduce
   - Uses Resilience4j for fault tolerance
   - Database: MySQL

4. Cart Microservice
   - Manages shopping cart
   - Tables: cart, cart_items
   - APIs: POST /cart/add, DELETE /cart/remove
   - Database: Redis / MySQL

5. Order Microservice (Core Orchestration Service)
   - Tables: orders, order_items
   - APIs: POST /orders, GET /orders/{id}
   - Kafka Producer: ORDER_CREATED event
   - Database: MySQL

6. Payment Microservice
   - Handles payment processing
   - Table: payments
   - API: POST /payments
   - Kafka Events: PAYMENT_SUCCESS, PAYMENT_FAILED
   - Database: MySQL

7. Notification Microservice
   - Sends email/SMS notifications
   - Kafka Consumer
   - Events: ORDER_CREATED, PAYMENT_SUCCESS
   - No Database

8. Review & Rating Microservice
   - Manages product reviews and ratings
   - Table: reviews
   - APIs: POST /reviews, GET /reviews/product/{id}
   - Database: MySQL

## Infrastructure Services

1. Config Server
   - Centralized configuration management
   - Reads configuration from Git repository
   - Provides configs to all microservices

2. Eureka Server
   - Service discovery and registration

3. API Gateway
   - Single entry point for all client requests
   - Routing, logging, and rate limiting
  
## Technology Stack

- Java & Spring Boot
- Spring Cloud Config Server
- Eureka Service Discovery
- Spring Cloud Gateway
- Spring Data JPA
- Apache Kafka (Event-driven communication)
- Resilience4j (Fault tolerance & retries)
- MySQL & Redis
- Zipkin (Distributed tracing)

## Key Features

- Microservices-based architecture
- Centralized configuration management
- Service discovery and load balancing
- API Gateway as a single entry point
- Event-driven communication using Kafka
- Fault tolerance with Resilience4j
- Distributed tracing with Zipkin & Sleuth
- Scalable and cloud-ready design
