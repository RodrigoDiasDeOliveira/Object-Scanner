import os

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

MODEL_PATH = os.path.join(BASE_DIR, "model", "best.pt")
DATASET_CONFIG = os.path.join(BASE_DIR, "dataset", "data.yaml")
