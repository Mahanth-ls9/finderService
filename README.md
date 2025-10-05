FinderService --> Backend 
This project is a Spring Boot application with JWT-based authentication, Swagger API documentation, and a React frontend.

Prerequisites

Java 17+

Maven 3.8+

Node.js 18+ and npm/yarn

Optional: Postman for testing APIs

Optional: H2 Database console available at http://localhost:8080/h2-console

Backend Setup (Spring Boot)

Clone the repository

git clone <your-repo-url>
cd finder-service


Build and run the backend

# Using Maven
mvn clean install
mvn spring-boot:run


Verify the backend is running

Swagger UI: http://localhost:8080/swagger-ui/index.html

OpenAPI JSON: http://localhost:8080/v3/api-docs

H2 Console: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (leave empty)
