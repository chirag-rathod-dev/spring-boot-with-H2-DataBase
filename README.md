# Spring Boot with H2 Database

## Overview
This project demonstrates a simple Spring Boot application with an H2 in-memory database. It includes basic CRUD operations for managing products.

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6.0 or higher

### Installation
1. **Clone the repository:**
   ```sh
   git clone https://github.com/chirag-rathod-dev/spring-boot-with-H2-DataBase.git
   cd spring-boot-with-H2-DataBase
    ```
2. **Build the project using Maven:**
3. ```sh
   mvn clean package
   ```
4. **Run the application:**
   ```sh
   java -jar target/spring-boot-with-h2-database.jar
   ```
5. **Access the H2 database console at http://localhost:8080/h2-console**
6. **Database Credentials:**   
   - JDBC URL: jdbc:h2:mem:test
   - Username: sa
   - Password: (leave it blank)

## API Endpoints
The application provides the following API endpoints:
- `GET /products`: Retrieves a list of all products.
- `GET /products/{id}`: Retrieves a specific product by its ID.
- `POST /products`: Creates a new product.
- `PUT /products/{id}`: Updates an existing product by its ID.
- `DELETE /products/{id}`: Deletes a product by its ID.

## Project Structure
The project is structured as follows:
- `src/main/java`: Contains the Java source code for the application.
- `src/main/resources`: Contains configuration files and resources.
- `src/test/java`: Contains the unit tests for the application.
- `pom.xml`: Maven project configuration file.

## Author
Chirag Rathod

## Conclusion
This project provides a basic example of a Spring Boot application with an H2 in-memory database. It demonstrates the usage of Spring Data JPA, H2, and Maven to manage and access data.

