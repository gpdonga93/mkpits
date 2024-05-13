

## Introduction

This project implements a RESTful API for managing employees and their addresses.

### API Endpoints

POST /api/employees: Create a new employee

GET /api/employees: Get all employees without and with criteria (name)

POST /api/employees/{employeeId}/address: Create a new address for an employee

PUT /api/employees/{employeeId}: Update an existing employee

PUT /api/employees/{employeeId}/address/{addressId}: Update an address of an employee

GET /api/employees/{employeeId}: Get an employee by ID

GET /api/employees/{employeeId}/addresses: Get all addresses of an employee

DELETE /api/employees/{employeeId}: Delete an employee by ID


## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL (mkpits database.sql)
- Maven for dependency management
- Postman for API documentation (Mkpits Collections.postman_collection.json)

## Getting Started

Follow these instructions to set up and run the project on your local machine.

### Prerequisites

Make sure you have the following software installed:

- Java Development Kit (JDK)
- Maven
- MySQL

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/gpdonga93/mkpits.git

2. Setup database
   find attached database backup file (mkpits database.sql) and import into local.
   Update the database configuration in application.properties file with your database credentials.

4. Postman setup
   find attached postman RestApi collection file (Mkpits Collections.postman_collection.json) and import into postman tool.


