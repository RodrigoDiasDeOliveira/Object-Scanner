ADR-010: Model Export Strategy
Status: Accepted
Decision: Export to ONNX for server use and TFLite for future on-device enhancements.
Rationale: Support both cloud processing and potential offline scanning for TLP data entry.
Alternatives Considered: Only server-side.
Pros:

Future-proof (offline capability)
Performance flexibility

Cons:

Additional testing required

Consequences: Path toward hybrid (online + offline) data capture for TLP.
