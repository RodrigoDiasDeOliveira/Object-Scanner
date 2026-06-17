ADR-006: Database
Status: Accepted
Decision: Use Oracle Database for local/cloud persistence of scan records.
Rationale: Store raw scans and processed data as backup and audit trail before/during synchronization with TLP.
Alternatives Considered: PostgreSQL, MongoDB.
Pros:

Enterprise compliance and integration with existing systems
Strong support for audit and history

Cons:

Higher operational complexity

Consequences: Dual storage strategy: Oracle + TLP system.
