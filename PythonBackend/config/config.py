import os

class Config:
    AZURE_COGNITIVE_SERVICE_URL = "https://<sua_regiao>.api.cognitive.microsoft.com/vision/v3.0/detect"
    AZURE_COGNITIVE_SERVICE_KEY = os.getenv("AZURE_COGNITIVE_SERVICE_KEY")
