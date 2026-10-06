# PaperLedger Roadmap

Vision: a production-style paper-trading platform with stock search,
simulated orders, a double-entry ledger, news, price prediction, and
(later) AI features. Trading is always simulated.

Rule: finish and deploy each phase before starting the next.
Only Phase 1 is active. See MVP.md.

## Phase 1: Core (MVP)
Auth, stock search, delayed quotes, market orders, double-entry ledger,
portfolio and P&L, minimal UI, deployed.

Design decisions to make now so later phases are easy:
- Ledger is generic (accounts + entries), so fees and limit-order cash
  reservations can be added later without redesign.
- Quote provider sits behind an interface, so providers can be swapped.
- Order status is a proper state machine (pending, filled, cancelled),
  even though the MVP only uses market orders.
- Modules stay separated (auth, marketdata, orders, ledger, portfolio).

## Phase 2: Product
- UI overhaul with one design system and a proper charting library
- Limit orders and cancellation, with cash reserved in the ledger
- News tab: provider API, cached, with source links
- Price prediction in the Python service: compare against a naive
  baseline, show backtest results honestly, label as educational
- Observability: structured logs, metrics, request tracing

Design implications:
- Prediction runs as a separate Python service called through a
  small API. Spring Boot stays the source of truth.
- News and predictions are read-only features. They never touch
  the ledger.

## Phase 3: AI
- RAG first: answer questions over news and the user's own
  transactions, with citations and evals
- Single agent with read-only tools (portfolio lookup, news search,
  ledger queries). It never places orders on its own.
- Multi-agent only if a real use case needs it
- Evals: a test set with expected facts, tracking accuracy, latency,
  and cost per request

Design implications:
- AI service has read-only access (separate DB user or API).
- Log every LLM call: prompt version, tokens, latency, cost.
- Guardrails documented in the README: no trade advice, no write actions.

## Later ideas (not committed)
- Real-time quotes over WebSockets
- Leaderboards or multi-user competitions
- Social login, Stripe test-mode deposits
- Splitting a module into its own service only if a real boundary appears