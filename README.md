# true-north-challange-api

## Overview
This is a Spring Boot application that utilizes various dependencies to provide a robust 
backend solution. The application incorporates Spring Boot's features for web development, 
data access, security, and database migrations using FlywayDB.

## Features
- RESTful Web Services with Spring Boot
- JPA for data access and manipulation
- Hibernate Validator for input validation
- PostgreSQL as the database
- Database migrations using FlywayDB
- Security features with Spring Security
- JWT for authentication
- API documentation with Springdoc OpenAPI
- Lombok to reduce boilerplate code

## Prerequisites
- Java 11 or higher
- Gradle 6.0 or higher
- PostgreSQL database

## How to use
### Build
```bash {"id":"01J6SXWD48YJJ9X1ARV51MEKBC"}
gradle build
```
### Run local
```bash {"id":"01J6SXWD48YJJ9X1ARV51MEKBC"}
gradle bootRun
```
### Run tests
```bash {"id":"01J6SXWD48YJJ9X1ARV51MEKBC"}
gradle test
```
### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.2/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.2/gradle-plugin/packaging-oci-image.html)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

## Env Vars
There are two sets of env vars that you will need to configure 
when you deploy the project.

### Authentication
* `JWT_SECRET` : Secret used to generate the JWT token.
* `ENCRYPT_SECRET` : Secret for encrypting the user's password in the DB.

## DB
* `DB_URL` : This is the base jdbc URL of your database.
* `DB_USERNAME` : The user name of your DB account.
* `DB_PASSWORD` : The password of your DB account