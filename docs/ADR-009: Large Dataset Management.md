ADR-009: Large Dataset Management
Status: Accepted
Decision: Do not commit large datasets to Git. Use Roboflow + clear setup instructions.
Rationale: Warehouse datasets are large and change frequently.
Alternatives Considered: Git LFS.
Pros:

Lightweight repository
Easier collaboration

Cons:

Requires internet for initial setup

Consequences: Documentation must clearly explain dataset acquisition for TLP-related classes.
