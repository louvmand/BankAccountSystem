# Bank Account System

## Overview
This is a **Java-based project** built with **Quarkus** and **Maven**. It includes functionality for managing bank accounts and fetching exchange rates using an external API.

## Prerequisites
Before starting, ensure you have the following installed:
- **Java 22**
- **Maven 3.8.1**

## How to Start the Program
You can run it with IntelliJ IDEA which is the IDE I used when I made it or follow the following steps.

### Build the Project
Run the following command to build the project and resolve dependencies:

```sh
mvn clean install
```

### Start the Application
Use Quarkus development mode to start the application:

```sh
mvn quarkus:dev
```

The application will start on **[http://localhost:8080](http://localhost:8080).**

### Access the Application
- Open your browser and navigate to **[http://localhost:8080](http://localhost:8080).**
- Use the **OpenAPI documentation** at **[http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)** to explore the API.

## How to Run the Tests

### Unit Tests
Run the following command to execute all unit tests:

```sh
mvn test
```

## Notes
- The application uses an **H2 in-memory database** for development and testing.
- Ensure you have an **active internet connection** to fetch exchange rates from the external API.
- The project leverages **Quarkus** for its lightweight and fast runtime capabilities.
