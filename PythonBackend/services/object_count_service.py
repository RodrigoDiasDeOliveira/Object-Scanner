from services.ml_detection_service import MLDetectionService
from services.azure_service import AzureService


class ObjectCountService:
    def __init__(self):
        self.ml_service = MLDetectionService()
        self.azure_service = AzureService()

    def count_objects(self, image_path):
        try:
            detections = self.ml_service.detect_objects(image_path)

            count = {}
            for obj in detections:
                name = str(obj["class"])
                count[name] = count.get(name, 0) + 1

            return count

        except Exception as e:
            print("Erro ML, fallback Azure:", e)
            return self.azure_service.detect_objects(image_path)
