Architecture Decision Records (ADRs) - Object Scanner
ADR-001: Overall System Architecture
Status: Accepted
Date: 2026-06-17
Decision: Adopt a Client-Server architecture with a native Android app as the mobile data capture tool and a Python Flask backend that integrates with the TLP system.
Rationale: The system must serve as an accessible data entry point for TLP, allowing field operators to scan items using standard smartphones instead of dedicated hardware.
Alternatives Considered: Dedicated industrial scanners, fully on-device processing, direct mobile-to-TLP integration.
Pros:

Democratizes data capture (any user with Android device)
Centralized processing and TLP integration
Easier maintenance and model updates

Cons:

Depends on network connectivity
Backend becomes a critical integration point

Consequences: Strong focus on reliable TLP API integration and cloud synchronization.
