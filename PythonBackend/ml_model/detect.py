from ultralytics import YOLO
from .config import MODEL_PATH

class ObjectDetector:

    def __init__(self):
        self.model = YOLO(MODEL_PATH)

    def detect(self, image_path):
        results = self.model(image_path)

        detections = []
        for r in results:
            for box in r.boxes:
                detections.append({
                    "class": int(box.cls[0]),
                    "confidence": float(box.conf[0])
                })

        return detections


if __name__ == "__main__":
    detector = ObjectDetector()
    result = detector.detect("dataset/test.jpg")
    print(result)