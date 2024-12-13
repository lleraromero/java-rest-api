# REST API Example with Jersey, Gradle, and Hibernate (Java 11)

This repository contains a boilerplate project to create a REST API using Jersey, Gradle, and Hibernate for Java 11.

## Introduction

This project demonstrates how to build a RESTful API using:

- **Jersey** for building RESTful web services.
- **Hibernate** for ORM (Object-Relational Mapping).
- **Gradle** for project build automation.

## Features

- CRUD operations for sample entities
- Integration with a relational database using Hibernate
- Example endpoints with basic request/response patterns
- Easily extendable and customizable

## Getting Started

### Prerequisites

Ensure you have the following installed on your system:

- **Java 11**
- **Gradle** (or use the Gradle Wrapper included in the project)
- **PostgreSQL** (or any other supported relational database)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/lleraromero/java-rest-api.git
   cd java-rest-api
   ```

2. Configure the database:

   - Update `src/main/resources/hibernate.cfg.xml` with your database credentials.

3. Build the project:

   ```bash
   ./gradlew build
   ```

### Running the Application

1. Start the application:

   ```bash
   ./gradlew run
   ```

2. Access the API at `http://localhost:8080/api`.

## API Documentation

Sample endpoints include:

- `GET /api/employees` - Retrieve all employees.
- `POST /api/employees` - Create a new employee.
- `GET /api/employees/{id}` - Retrieve an employee by ID.
- `PUT /api/employees/{id}` - Update an employee by ID.
- `DELETE /api/employees/{id}` - Delete an employee by ID.

## Technologies Used

- **Java 11**
- **Gradle**
- **Jersey**
- **Hibernate**
- **PostgreSQL**
- **JUnit** for testing

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/my-feature`
3. Commit your changes: `git commit -m 'Add my feature'`
4. Push to the branch: `git push origin feature/my-feature`
5. Open a pull request

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

