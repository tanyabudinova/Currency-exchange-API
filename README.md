#Currency Rate Application
This Spring application fetches currency rates from an external API and provides endpoints to retrieve and manipulate currency rates.
##Features
- Fetch currency rates from an external API.
- Store currency rates in a database.
- Retrieve currency rates for specific currencies.
- Convert amounts between different currencies.
- Retrieve historical currency conversions.

##Technologies Used
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Postgres
- External API ([currencyapi.com](https://currencyapi.com/))

##Getting Started

###Prerequisites
- Java (version 17 or above)

###Usage

####Docker
1. Build the JAR file.
```shell
./gradlew bootJar
```
2. Run docker compose.
```shell
docker-compose up --build
```

####Local
1. Run the Postgres DB.
```shell
docker compose up -d db
```
2. Run the application.
```shell
./gradlew bootRun
```