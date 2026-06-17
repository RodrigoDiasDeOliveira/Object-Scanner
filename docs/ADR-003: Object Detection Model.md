ADR-003: Object Detection Model
Status: Accepted
Decision: Use YOLOv8 (Ultralytics) as the primary model for detecting boxes, pallets, labels, and other warehouse items.
Rationale: The system needs to identify and count multiple item types to enrich data sent to TLP.
Alternatives Considered: YOLOv11, custom Azure models, traditional barcode scanners only.
Pros:

Multi-object detection capability
Good performance on industrial objects
Can be fine-tuned with warehouse-specific datasets

Cons:

Requires continuous dataset improvement

Consequences: Model must be trained to recognize TLP-relevant classes (pallets, boxes, labeled items, etc.).
