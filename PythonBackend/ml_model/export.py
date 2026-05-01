from ultralytics import YOLO
from .config import MODEL_PATH


def export():
    model = YOLO(MODEL_PATH)

    model.export(format="onnx")
    model.export(format="tflite")

if __name__ == "__main__":
    export()