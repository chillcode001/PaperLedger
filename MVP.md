# PaperLedger MVP

## Goal
A user can sign up, search a stock, see a delayed quote, place a simulated
market order, and see cash, positions, and order history update correctly.

## Definition of done
- Whole app runs with `docker compose up`
- Tests pass locally and in GitHub Actions
- Deployed with a live link
- README explains architecture, design decisions, and limitations

## In scope
- Auth (JWT)
- Double-entry ledger for cash and positions
- Stock search and delayed quotes (cached)
- Market buy/sell orders with idempotency
- Portfolio and profit/loss
- Minimal React UI

## Out of scope (after the first deploy)
Limit orders, news tab, price prediction, RAG and agents, Stripe,
social login, real-time websockets.

## Slices

### 1. Skeleton
- [ ] Postgres via docker-compose
- [ ] Flyway runs `V1__init.sql`
- [ ] `/actuator/health` returns UP
- [ ] One integration test with Testcontainers
- [ ] GitHub Actions runs the tests
- [ ] README has run instructions

### 2. Auth and accounts
- [ ] Register and login with JWT
- [ ] Each user gets an account
- [ ] Starting cash recorded as a ledger entry
- [ ] Tests: duplicate email, wrong password, protected endpoint

### 3. Ledger core
- [ ] Tables: accounts, ledger_transactions, ledger_entries
- [ ] Every transaction's entries balance (debits = credits)
- [ ] Cash can never go negative
- [ ] Tests: unbalanced transaction rejected, concurrent transfers

### 4. Quotes
- [ ] Market-data interface plus one provider implementation
- [ ] Stock search endpoint
- [ ] Quote endpoint with Redis cache and TTL
- [ ] Tests use a mocked provider

### 5. Market orders
- [ ] Place buy and sell orders
- [ ] Idempotency key (unique) prevents duplicate orders
- [ ] Order fills create ledger entries and update positions
- [ ] Tests: insufficient funds, insufficient shares, duplicate request,
  two simultaneous orders

### 6. Portfolio
- [ ] Positions with average cost
- [ ] Unrealized profit/loss using current quotes
- [ ] Order history endpoint

### 7. Minimal UI
- [ ] Login and signup
- [ ] Search and quote view
- [ ] Order form
- [ ] Portfolio and history pages
- [ ] Loading and error states

### 8. Ship it
- [ ] Deploy backend, database, and frontend
- [ ] Architecture diagram and screenshots in README
- [ ] Limitations section (simulated trading, delayed data)

## Current slice
Slice 1: Skeleton

## Notes and decisions
- (add short notes as you make decisions, e.g. why Maven, why Postgres)