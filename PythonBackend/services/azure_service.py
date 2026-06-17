import os
from dotenv import load_dotenv
import requests
from PIL import Image
import io

load_dotenv()

class AzureService:
    def __init__(self):
        self.subscription_key = os.getenv('AZURE_SUBSCRIPTION_KEY')
        self.endpoint = os.getenv('AZURE_ENDPOINT')

    def detect_objects(self, image_path):
        # Implementação simplificada - Azure Computer Vision
        try:
            with open(image_path, "rb") as image_data:
                headers = {
                    'Ocp-Apim-Subscription-Key': self.subscription_key,
                    'Content-Type': 'application/octet-stream'
                }
                response = requests.post(
                    f"{self.endpoint}/vision/v3.2/analyze?visualFeatures=Objects",
                    headers=headers,
                    data=image_data
                )
                response.raise_for_status()
                result = response.json()
                # Processar para contagem simples
                count = {}
                for obj in result.get('objects', []):
                    name = obj.get('object', 'unknown')
                    count[name] = count.get(name, 0) + 1
                return count
        except Exception as e:
            return {"error": f"Azure fallback falhou: {str(e)}"}