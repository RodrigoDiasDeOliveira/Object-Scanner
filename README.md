# Object-Scanner
![detector](https://github.com/user-attachments/assets/37645298-c6ae-4051-8510-b6c02c6d69dc)

Este projeto visa desenvolver um scanner de objetos com IA para contagem automática de materiais em ambientes industriais. Utiliza Java para Android, um backend em Python com Flask, Machine Learning (YOLOv8) e Azure Cognitive Services como fallback, além de Oracle para armazenamento.

# Projeto de Scanner de Objetos com IA

Este projeto é um sistema de scanner de objetos que utiliza inteligência artificial para reconhecer e contar materiais em um ambiente industrial. O sistema é composto por um aplicativo Android que captura imagens e um backend em Python que processa essas imagens usando YOLOv8 (via Ultralytics) com fallback para Azure Cognitive Services.

## Estrutura do Projeto

```
Object-Scanner/
├── PythonBackend/
│   ├── ml_model/
│   │   ├── config.py          # Configurações de caminhos
│   │   ├── train.py           # Treinamento do modelo YOLOv8
│   │   ├── detect.py          # Classe ObjectDetector
│   │   ├── export.py          # Exportação do modelo
│   │   ├── dataset/           # Dados de treinamento
│   │   │   ├── data.yaml      # Configuração do dataset
│   │   │   ├── images/        # Imagens de treino/validação
│   │   │   └── labels/        # Anotações YOLO
│   │   └── model/             # Modelos treinados
│   ├── services/
│   │   ├── ml_detection_service.py    # Serviço ML
│   │   ├── object_count_service.py    # Contagem com fallback
│   │   ├── azure_service.py           # Integração Azure
│   │   └── azure_vision.py            # Análise Azure
│   ├── utils/
│   ├── config/
│   ├── app.py                 # API Flask
│   ├── requirements.txt       # Dependências
│   └── setup_en.sh            # Script de setup
├── Android/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/androidapp/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── CameraHelper.java
│   │   │   │   ├── RetrofitClient.java
│   │   │   │   ├── controller/ObjectScanController.java
│   │   │   │   ├── model/ObjectCount.java
│   │   │   │   └── services/ObejctScanService.java
│   │   │   └── resources/
│   │   │       └── AndroidManifest.xml
│   │   └── build.gradle
│   └── resources/
│       └── application.properties
├── OracleDatabase/
│   ├── config/
│   │   ├── db_config.properties
│   │   └── db_config.py
│   ├── migrations/
│   │   └── initial_migration.sql
│   └── schema/
│       └── create_tables.sql
├── docs/
│   └── architecture.md
├── README.md
├── .gitignore
└── tri mind ai solutions.jpg
```

## Tecnologias Utilizadas

- **Frontend (Android)**: Java, Android SDK
- **Backend (Python)**: Flask, YOLOv8 (Ultralytics), Azure Cognitive Services
- **Machine Learning**: PyTorch, OpenCV
- **Banco de Dados**: Oracle (Database on OCI)
- **Ferramentas**: Gradle, Git, pip

## Funcionalidades

- **Captura de Imagens**: Aplicativo Android captura imagens via câmera
- **Detecção ML**: Processamento com YOLOv8 para detecção precisa
- **Fallback Azure**: Azure Cognitive Services como backup
- **Contagem Automática**: Conta objetos detectados por classe
- **API REST**: Endpoint `/detect` para upload e análise
- **Treinamento Customizado**: Scripts para treinar modelos próprios

## Instalação

### Requisitos

- Python 3.8+
- Java 8+
- Android Studio
- Oracle Database
- GPU recomendada para treinamento ML

### Configuração do Backend

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/RodrigoDiasDeOliveira/Object-Scanner.git
   cd Object-Scanner/PythonBackend
   ```

2. **Crie ambiente virtual**:
   ```bash
   python -m venv venv
   source venv/bin/activate  # Linux/Mac
   # ou venv\Scripts\activate no Windows
   ```

3. **Instale dependências**:
   ```bash
   pip install -r requirements.txt
   ```

4. **Configure variáveis de ambiente**:
   ```bash
   export AZURE_SUBSCRIPTION_KEY='sua_chave_azure'
   export AZURE_ENDPOINT='https://seu-endpoint.cognitiveservices.azure.com/'
   ```

5. **Treine o modelo (opcional)**:
   ```bash
   cd ml_model
   python train.py
   ```

6. **Execute o servidor**:
   ```bash
   python app.py
   ```

### Configuração do Android

1. Abra o projeto `Android/` no Android Studio
2. Configure `application.properties` com URLs do backend
3. Permissões de câmera no `AndroidManifest.xml`
4. Build e execute no dispositivo/emulador

## Como Usar

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

### Treinamento do Modelo

1. Prepare dataset em `ml_model/dataset/`
2. Configure `data.yaml`
3. Execute `python ml_model/train.py`
4. Modelo salvo em `ml_model/model/best.pt`

### Exportação do Modelo

```bash
python ml_model/export.py
```

Gera arquivos `.onnx` e `.tflite` para deploy.

## Desenvolvimento

### Adicionar Nova Classe de Objeto

1. Atualize `ml_model/dataset/data.yaml`:
   ```yaml
   names:
     0: objeto
     1: novo_objeto
   ```

2. Adicione imagens anotadas
3. Retreine o modelo

### Testar Detecção

```python
from ml_model.detect import ObjectDetector

detector = ObjectDetector()
result = detector.detect("caminho/para/imagem.jpg")
print(result)  # [{"class": 0, "confidence": 0.85}, ...]
```

## Contribuições

Contribuições são bem-vindas! Abra issues ou PRs.

## Licença

Licenciado sob MIT License.

## Contato

Rodrigo Dias de Oliveira  
rodrigo.digau@gmail.com  

Construído sob supervisão de TRI MIND LABS Solutions.  
![TRI MIND AI Solutions](tri%20mind%20ai%20solutions.jpg)
