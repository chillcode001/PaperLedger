# PaperLedger

Simulated ("paper") stock-trading platform with a double-entry ledger.
Trading is simulated. No real money is ever involved.

## Stack
- Backend: Java [17], Spring Boot, Spring Data JPA, PostgreSQL, Flyway, Redis (later)
- Build tool: [Maven or Gradle: pick one and delete the other]
- AI/prediction service (later phase): Python + FastAPI, separate service
- Frontend: React + TypeScript
- Infra: Docker Compose, GitHub Actions CI
- Tests: JUnit 5, Testcontainers (real Postgres in integration tests)

## Commands
- Start database: `docker compose up -d`
- Run app: [`./mvnw spring-boot:run` or `./gradlew bootRun`]
- Run tests: [`./mvnw test` or `./gradlew test`]
- Health check: `GET http://localhost:8080/actuator/health`

## Architecture rules
- Modular monolith. Modules: auth, marketdata, orders, ledger, portfolio.
  Modules talk through public interfaces, never by reading each other's tables.
- Create a module package only when the slice needs it.
- Every money movement is a ledger transaction. Total debits must equal
  total credits.
- Money is `BigDecimal` in Java and `NUMERIC` in Postgres. Never float or double.
- Order requests require an idempotency key. Duplicate requests must not
  be applied twice.
- Balances and positions must never go negative.
- Quote/market-data provider sits behind an interface so tests can mock it.
- Database changes only through Flyway migrations. Never edit an applied
  migration; add a new one.
- Configuration and secrets come from environment variables, never hardcoded.

## Code conventions
- Layers: controller -> service -> repository. No business logic in controllers.
- Validate all request input. Return consistent error responses.
- Use DTOs at the API boundary; do not expose JPA entities directly.
- Write tests for ledger and order logic before or alongside the code.

## Teaching mode (strict)
I am a junior developer rebuilding my skills. I write ALL code.
- Never create or edit source files unless I explicitly say "you may write this".
- For each slice, first give: goal, concepts to know, a step plan, and
  acceptance criteria. No code.
- When I ask for help, give hints and guiding questions before answers.
- When I say "review", read my code and give feedback in this order:
    1. Bugs and correctness (edge cases, concurrency)
    2. Design and structure
    3. Missing tests
    4. Industry best practice, with the reason behind it
       Point to the file and line. Do not rewrite my code; show a short snippet
       only if the idea is hard to explain in words.
- After each review, quiz me with 3 questions about what I wrote.
- Keep explanations short and concrete, and tie them back to the ledger.
- If I am stuck for a long time, offer a bigger hint or a worked example
  of a similar (not identical) problem.

## Git and safety
- Small commits with clear messages. One slice per branch.
- Never commit secrets, `.env` files, `.idea`, or `.DS_Store`.
- Do not run destructive commands (force push, dropping databases,
  deleting files) without asking me first.

## Current focus
@MVP.md