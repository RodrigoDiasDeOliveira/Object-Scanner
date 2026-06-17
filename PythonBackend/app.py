from flask import Flask, request, jsonify
from flask_cors import CORS
from services.object_count_service import ObjectCountService
import os
from dotenv import load_dotenv

load_dotenv()

app = Flask(__name__)
CORS(app)

UPLOAD_FOLDER = "uploads"
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

service = ObjectCountService()

@app.route("/detect", methods=["POST"])
def detect():
    if 'image' not in request.files:
        return jsonify({'error': 'Nenhuma imagem enviada'}), 400
    
    file = request.files['image']
    if file.filename == '':
        return jsonify({'error': 'Nome de arquivo inválido'}), 400
    
    # Validação simples
    if not file.filename.lower().endswith(('.png', '.jpg', '.jpeg')):
        return jsonify({'error': 'Formato inválido'}), 400
    
    filepath = os.path.join(UPLOAD_FOLDER, file.filename)
    file.save(filepath)
    
    try:
        result = service.count_objects(filepath)
        # Opcional: deletar arquivo após processamento
        # os.remove(filepath)
        return jsonify(result)
    except Exception as e:
        return jsonify({'error': str(e)}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=int(os.getenv('PORT', 5000)), debug=True)