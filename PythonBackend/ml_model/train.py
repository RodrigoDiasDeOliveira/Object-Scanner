from ultralytics import YOLO
from .config import DATASET_CONFIG


def train():
    model = YOLO("yolov8n.pt")

    model.train(
        data=DATASET_CONFIG,
        epochs=5,
        imgsz=640,
        batch=8,
        name="object_scanner_model"
    )


if __name__ == "__main__":
    train()
