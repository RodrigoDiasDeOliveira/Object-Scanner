ADR-011: Image Processing & Data Flow
Status: Accepted
Decision: Process images on backend, extract structured data (counts, classes, confidence), and send enriched payload to TLP + cloud storage.
Rationale: Mobile app should focus on capture; backend handles intelligence and integration.
Alternatives Considered: Direct mobile-to-TLP.
Pros:

Better accuracy and control
Easier to add business rules

Cons:

Latency in data delivery

Consequences: Defines clear contract between mobile → backend → TLP.
