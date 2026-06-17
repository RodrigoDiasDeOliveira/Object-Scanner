import os
from dotenv import load_dotenv

load_dotenv()

# Caminhos
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
MODEL_PATH = os.getenv('MODEL_PATH', os.path.join(BASE_DIR, 'model/best.pt'))
DATASET_PATH = os.path.join(BASE_DIR, 'dataset')
UPLOAD_FOLDER = os.path.join(BASE_DIR, '../uploads')

# Configurações de treinamento
IMG_SIZE = 640
BATCH_SIZE = 16
EPOCHS = 100