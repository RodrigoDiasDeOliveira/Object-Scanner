# ==================== IMPORTS ====================
from ultralytics import YOLO
import shutil
from pathlib import Path

# Configuração (corrigido)
from ml_model.config import DATASET_PATH, IMG_SIZE, BATCH_SIZE, EPOCHS


# ==================== TREINAMENTO ====================
def train_model():
    model = YOLO("yolov8n.pt")  # ou yolov8s.pt, yolov8m.pt etc.

    results = model.train(
        data=DATASET_PATH,
        imgsz=IMG_SIZE,
        batch=BATCH_SIZE,
        epochs=EPOCHS,
        name="object_scanner_model",
        project="runs/train",
        exist_ok=True,
        pretrained=True,
        optimizer="auto",
        seed=42
    )

    # ==================== SALVAMENTO DO MELHOR MODELO ====================
    # Caminho do melhor modelo gerado pelo YOLOv8
    best_model_path = Path(results.save_dir) / "weights" / "best.pt"
    
    # Caminho de destino desejado
    target_path = Path("ml_model/model/best.pt")
    target_path.parent.mkdir(parents=True, exist_ok=True)

    if best_model_path.exists():
        shutil.copy(best_model_path, target_path)
        print(f"✅ Modelo salvo com sucesso em: {target_path}")
    else:
        print("⚠️  Arquivo best.pt não encontrado!")

    # Opcional: também copiar o último modelo
    last_model_path = Path(results.save_dir) / "weights" / "last.pt"
    if last_model_path.exists():
        shutil.copy(last_model_path, target_path.parent / "last.pt")


if __name__ == "__main__":
    train_model()