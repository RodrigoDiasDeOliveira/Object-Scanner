ADR-002: Mobile Development Framework
Status: Accepted
Decision: Develop using Native Android with Java + CameraX.
Rationale: High-quality image capture is essential for reading labels, barcodes, RFIDs (via camera), and detecting boxes/pallets in real industrial environments.
Alternatives Considered: Flutter, Kotlin Multiplatform, React Native.
Pros:

Best camera performance and control
Reliable in challenging lighting conditions
Direct integration with future RFID/NFC hardware

Cons:

Android-only (initial scope)

Consequences: Enables high-accuracy scanning directly feeding TLP.
