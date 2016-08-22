[![Build Status](https://travis-ci.org/akotynski/spring-boot-data-mongo-web-example.svg?branch=master)](https://travis-ci.org/akotynski/spring-boot-data-mongo-web-example)

# Example spring-boot project.

## Used components:

- spring-data with mongodb
- spring-security - oauth2 facebook login
- springfox swagger documentation
- RestAssured for integration tests

## Requirements: only JDK8

## How to run?

`./gradlew bootRun`

Now server is running on http://localhost:8080/ !

## Available endpoints:

- CRUD on `/students`
- `/me` - user information - automatic redirection to facebook page

Api swagger documentation available on http://localhost:8080/swagger-ui.html
