from flask import Flask, request, jsonify
import cv2
import numpy as np
from TensorFlowModel.object_detector import ObjectDetector

app = Flask(__name__)
detector = ObjectDetector()

@app.route("/detect", methods=["POST"])
def detect_objects():
    file = request.files["image"]
    image = cv2.imdecode(np.frombuffer(file.read(), np.uint8), cv2.IMREAD_COLOR)
    
    results = detector.detect(image)
    
    return jsonify({"detections": results.tolist()})

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
