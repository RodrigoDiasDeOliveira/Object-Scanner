import os
import requests

class AzureService:
    def __init__(self):
        self.subscription_key = os.getenv('AZURE_SUBSCRIPTION_KEY')
        self.endpoint = os.getenv('AZURE_ENDPOINT')

    def analyze_image(self, image):
        analyze_url = f"{self.endpoint}/vision/v3.2/analyze"

        # Parâmetros para o tipo de análise desejada
        params = {
            'visualFeatures': 'Categories,Description,Color',
            'details': '',
        }

        headers = {
            'Content-Type': 'application/octet-stream',
            'Ocp-Apim-Subscription-Key': self.subscription_key,
        }

        response = requests.post(analyze_url, headers=headers, params=params, data=image)
        response.raise_for_status()  # Levanta um erro se a requisição falhar
        return response.json()

    def detect_objects(self, image_path):
        with open(image_path, 'rb') as image_file:
            image_bytes = image_file.read()

        result = self.analyze_image(image_bytes)
        detections = {}

        if 'categories' in result:
            for category in result['categories']:
                name = category.get('name')
                if name:
                    detections[name] = detections.get(name, 0) + 1

        return detections
