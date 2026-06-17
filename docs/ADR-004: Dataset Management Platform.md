ADR-004: Dataset Management Platform
Status: Accepted
Decision: Use Roboflow for dataset creation, annotation, and versioning focused on warehouse/TLP items.
Rationale: Need high-quality annotated data for labels, RFIDs (visual), boxes, pallets, and other logistics assets.
Alternatives Considered: Manual annotation tools only.
Pros:

Fast iteration on warehouse-specific scenarios
Augmentations suitable for industrial environments

Cons:

Ongoing effort to keep dataset updated with new item types

Consequences: Dataset becomes a living asset aligned with TLP requirements.
