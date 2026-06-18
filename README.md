# Object-Scanner
![detector](https://github.com/user-attachments/assets/37645298-c6ae-4051-8510-b6c02c6d69dc)

Instead of relying on expensive specialized hardware, users can use their Android device to scan labels, barcodes, RFIDs (visually), pallets, boxes, and other warehouse items. The captured data is processed and automatically sent to the TLP system and stored in the cloud.

📋 Project Overview
This project combines a Native Android App with a Python Flask Backend to provide real-time object detection and counting using YOLOv8, with Azure Computer Vision as fallback. All data is persisted in Oracle Database and integrated with the TLP system.

Key Features

Real-time object detection & counting using YOLOv8
Mobile-first scanning using standard Android smartphones
Azure fallback for robustness in challenging environments
Seamless integration with TLP system
Audit trail in Oracle Database
Cloud synchronization
Custom model training support via Roboflow

Object-Scanner/
├── PythonBackend/
│   ├── app.py                          # Flask API
│   ├── requirements.txt
│   ├── setup_en.sh
│   ├── .env.example
│   ├── ml_model/
│   │   ├── train.py
│   │   ├── detect.py
│   │   ├── export.py
│   │   ├── config.py
│   │   └── dataset/                    # Downloaded from Roboflow
│   ├── services/
│   │   ├── object_count_service.py
│   │   ├── ml_detection_service.py
│   │   └── azure_service.py
│   └── config/
│       └── db_config.py                # Oracle connection
├── Android/
│   ├── app/
│   │   ├── src/main/java/com/example/objectscanner/
│   │   └── src/main/res/layout/
│   ├── build.gradle
│   └── settings.gradle
├── OracleDatabase/
│   └── schema/
│       └── create_tables.sql
├── docs/
│   ├── ADRs/                           # Architecture Decision Records
│   └── architecture.md
├── .github/workflows/                  # CI/CD
├── README.md
└── .gitignore
```

Technologies Used:
MobileJava + CameraX + RetrofitBackend
Python + FlaskAI/MLYOLOv8 (Ultralytics) + RoboflowFallback
Azure Computer VisionDatabase
Oracle DatabaseDeployment
Docker + Cloud (Railway/Oracle Cloud)CI/CDGitHub Actions


 Installation & Setup
Backend
git clone https://github.com/RodrigoDiasDeOliveira/Object-Scanner.git
cd Object-Scanner/PythonBackend

# Setup environment
bash setup_en.sh

# Configure environment variables
cp .env.example .env
# Edit .env with your credentials
Run the server:
python app.py
Android App

Open the Android/ folder in Android Studio
Update the backend URL in RetrofitClient.java
Build and run on a physical device (recommended for camera)


📸 How to Use
1. Using the Mobile App

Open the app
Point camera at pallets, boxes, or labeled items
Tap "Capture & Analyze"
Results are sent automatically to TLP

2. API Endpoint
curl -X POST -F "image=@sample.jpg" http://localhost:5000/detect
Sample Response:
JSON{
  "success": true,
  "counts": {
    "pallet": 12,
    "box": 45,
    "sack": 8
  },
  "source": "ml",
  "scan_id": 784
}

## 🧠 Model Training

The model can be customized for your specific warehouse items (pallets, boxes, labels, etc.).

### Steps:

1. **Download Dataset**
   - Access [Roboflow](https://universe.roboflow.com)
   - Choose or fork a warehouse/industrial dataset
   - Add your own images and generate a new version
   - Export in **YOLOv8** format and download the zip

2. **Extract Dataset**
   ```bash
   cd PythonBackend/ml_model
   unzip ~/Downloads/your-roboflow-dataset.zip -d dataset
python export.py



### API Endpoint

Envie imagem POST para `/detect`:

```bash
curl -X POST -F "image=@imagem.jpg" http://localhost:5000/detect
```

Resposta:
```json
{
  "0": 3,
  "1": 1
}
```


Documentation

Architecture Decision Records (ADRs)
System Architecture


🤝 Contributing
Contributions are welcome! Feel free to open issues or submit Pull Requests.

📜 License
This project is licensed under the MIT License.

