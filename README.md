# Example spring-boot project.

## Used components:

- spring-data with mongodb
- spring-security - oauth2 facebook login
- springfox swagger documentation

## Requirements: only JDK8

## How to run?

`./gradlew bootRun`

Now server is running on `http://localhost:8080/` !

## Available endpoints:

- CRUD on /students
- /me - user information - automatic redirection to facebook page

Api swagger documentation available on `http://localhost:8080/swagger-ui.html`
