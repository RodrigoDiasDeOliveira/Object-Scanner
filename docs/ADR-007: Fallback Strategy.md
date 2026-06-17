ADR-007: Fallback Strategy
Status: Accepted
Decision: Implement Azure Computer Vision as fallback, plus basic barcode/QR reading when ML detection fails.
Rationale: Industrial environments can be harsh (poor lighting, damaged labels). System must still feed data to TLP reliably.
Alternatives Considered: No fallback.
Pros:

Higher success rate in real-world conditions
Graceful degradation

Cons:

Additional costs and service dependencies

Consequences: Maximizes data capture reliability for TLP.
