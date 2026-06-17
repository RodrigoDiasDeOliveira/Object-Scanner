from services.ml_detection_service import MLDetectionService
from services.azure_service import AzureService
import os

class ObjectCountService:
    def __init__(self):
        self.ml_service = MLDetectionService()
        self.azure_service = AzureService()
        self.use_ml = True  # Mude para False se quiser forçar Azure

    def count_objects(self, image_path: str):
        result = {
            "success": True,
            "counts": {},
            "detections": [],
            "source": "ml"
        }

        try:
            if self.use_ml:
                # Tenta YOLO primeiro
                detections = self.ml_service.detect_objects(image_path)
                result["detections"] = detections
                
                # Conta por classe
                for det in detections:
                    cls = str(det["class"])
                    result["counts"][cls] = result["counts"].get(cls, 0) + 1
            else:
                raise Exception("Forçando fallback")

        except Exception as e:
            print(f"ML falhou: {e}. Usando Azure fallback...")
            result["source"] = "azure"
            try:
                azure_counts = self.azure_service.detect_objects(image_path)
                result["counts"] = azure_counts
            except Exception as azure_error:
                result["success"] = False
                result["error"] = str(azure_error)

        return result