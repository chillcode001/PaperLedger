# PaperLedger

A simulated ("paper") stock-trading platform with a double-entry ledger.
Trading is simulated. No real money is ever involved.

## Prerequisites

- Java 17
- Docker Desktop (running)

## Setup

Create a `.env` file in the project root with these variables. Choose your own values for the placeholders:

```
PG_USER=<database user>
PG_PASSWORD=<database password>
PG_DATABASE=<database name>
```

`.env` is ignored by git. Never commit it.

## Run

1. Start the database:

   ```
   docker compose up -d
   ```

2. Start the app. It reads `.env` from the project root automatically:

   ```
   ./mvnw spring-boot:run
   ```

3. Check the health endpoint. It should return `{"status":"UP"}`:

   ```
   curl http://localhost:8080/actuator/health
   ```

## Test

Docker must be running. The tests start their own temporary Postgres with Testcontainers, so they don't need `.env` or the compose database.

```
./mvnw test
```

## Architecture

- Spring Boot with Spring Data JPA, PostgreSQL, and Flyway for schema migrations
- Database schema changes live in `src/main/resources/db/migration/` as versioned SQL files
- Hibernate runs in `validate` mode, so Flyway owns the schema
- Money will be stored as `BigDecimal` and `NUMERIC`, never floating point

## Limitations

- Trading is simulated only.
- Market data will be delayed (planned).
