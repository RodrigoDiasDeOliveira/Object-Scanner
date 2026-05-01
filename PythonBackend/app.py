from flask import Flask, request, jsonify
from services.object_count_service import ObjectCountService
import os

app = Flask(__name__)
service = ObjectCountService()

UPLOAD_FOLDER = "uploads"
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

@app.route("/detect", methods=["POST"])
def detect():
    if 'image' not in request.files:
        return jsonify({'error': 'No image provided'}), 400

    file = request.files['image']
    filepath = os.path.join(UPLOAD_FOLDER, file.filename)
    file.save(filepath)

    result = service.count_objects(filepath)
    return jsonify(result)


if __name__ == '__main__':
    app.run(debug=True)

