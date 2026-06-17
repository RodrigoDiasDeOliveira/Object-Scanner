from ultralytics import YOLO
from config import DATASET_PATH, IMG_SIZE, BATCH_SIZE, EPOCHS
import os

def train_model():
    # Carrega modelo pré-treinado ou inicia do zero
    model = YOLO('yolov8n.pt')  # yolov8n = nano (rápido), yolov8m ou l para mais precisão
    
    data_yaml = os.path.join(DATASET_PATH, 'data.yaml')
    
    if not os.path.exists(data_yaml):
        print("❌ data.yaml não encontrado! Crie o dataset primeiro.")
        return
    
    print("🚀 Iniciando treinamento...")
    results = model.train(
        data=data_yaml,
        epochs=EPOCHS,
        imgsz=IMG_SIZE,
        batch=BATCH_SIZE,
        name='object_scanner',
        project='ml_model/runs/train'
    )
    
    # Salva o melhor modelo
    best_model = results.best  # ou model.export()
    best_model.save(os.path.join('model', 'best.pt'))
    print("✅ Treinamento concluído! Modelo salvo em ml_model/model/best.pt")

if __name__ == "__main__":
    train_model()