from ml_model.detect import ObjectDetector


class MLDetectionService:
    def __init__(self):
        self.detector = ObjectDetector()

    def detect_objects(self, image_path):
        return self.detector.detect(image_path)
