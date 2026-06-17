ADR-005: Backend Framework
Status: Accepted
Decision: Use Flask as the backend framework.
Rationale: Lightweight framework sufficient to receive scans from mobile, process images, and forward structured data to TLP and cloud storage.
Alternatives Considered: FastAPI, Django.
Pros:

Rapid development
Easy integration with YOLO and external APIs (TLP)

Cons:

May need migration if transaction volume grows significantly

Consequences: Backend acts as the integration layer between mobile scans and TLP.
