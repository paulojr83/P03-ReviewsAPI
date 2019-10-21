# P03-ReviewsAPI
You will build the MySQL part of persistence layer for a REST API that supports the customer reviews section of a product page in an ecommerce application. You will also build corresponding test cases. A skeleton Spring Boot application with starter code for REST controller and other necessary configuration will be provided. Instructions to setup MySQL will also be provided.


# Reviews API 
Supports operations for writing reviews and listing reviews for a product but with no sorting or filtering.

### Prerequisites
MySQL needs to be installed and configured. Instructions provided separately.

### Getting Started
* Configure the MySQL Datasource in application.properties.
* Add Flyway scripts in src/main/resources/db/migration.
* Define JPA Entities and relationships.
* Define Spring Data JPA Repositories.
* Add tests for JPA Repositories.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)