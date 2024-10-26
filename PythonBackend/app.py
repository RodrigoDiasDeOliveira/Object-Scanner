from flask import Flask, request, jsonify
from services.object_count_service import ObjectCountService
from config.config import Config

app = Flask(__name__)
app.config.from_object(Config)

object_count_service = ObjectCountService()

@app.route('/api/process', methods=['POST'])
def process_image():
    if 'image' not in request.files:
        return jsonify({'error': 'No image provided'}), 400
    
    image = request.files['image'].read()
    object_count = object_count_service.process_image(image)
    return jsonify(object_count), 200

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

