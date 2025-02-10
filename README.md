# Object-Scanner
![detector](https://github.com/user-attachments/assets/37645298-c6ae-4051-8510-b6c02c6d69dc)

Este projeto visa desenvolver um scanner de objetos com IA para contagem automática de materiais em ambientes industriais. Utiliza Java para Android, Spring Boot, um backend em Python com Azure Cognitive Services e Oracle para armazenamento.
Estrutura basica inicial

   
# Projeto de Scanner de Objetos com IA

Este projeto é um sistema de scanner de objetos que utiliza inteligência artificial para reconhecer e contar materiais em um ambiente industrial. O sistema é composto por um aplicativo Android que captura imagens e um backend em Python que processa essas imagens usando a Azure Cognitive Services.

## Estrutura do Projeto

A estrutura do projeto é a seguinte:

PythonBackend/ ├── app.py # Arquivo principal do Flask ├── services/ # Serviços de processamento │ ├── object_count_service.py # Serviço para contagem de objetos │ ├── azure_service.py # Integração com Azure Cognitive Services │ ├── azure_vision.py # Análise de imagem utilizando Azure ├── utils/ # Funções utilitárias ├── config/ # Configurações do projeto │ └── config.py # Configurações da API Azure └── requirements.txt # Dependências do backend Python

AndroidApp/ ├── src/main/java/com/example/androidapp/ │ ├── controller/ │ │ └── ObjectScanController.java # Controlador principal para chamadas de API │ ├── service/ │ │ └── ObjectScanService.java # Serviço que envia imagens ao backend Python │ ├── MainActivity.java # Captura de imagem e envio ao backend │ ├── CameraHelper.java # Utilitário para configuração da câmera │ ├── RetrofitClient.java # Cliente HTTP para comunicação │ └── model/ │ └── ObjectCount.java # Modelo de dados para a contagem ├── src/main/resources/ │ └── application.properties # Configurações de conexão com o banco Oracle e o backend Python └── build.gradle # Configurações do Gradle



## Tecnologias Utilizadas

- **Frontend (Android)**: Java, Android SDK
- **Backend (Python)**: Flask, Azure Cognitive Services
- **Banco de Dados**: Oracle (Database on OCI)
- **Ferramentas**: Maven, Gradle, Git

## Funcionalidades

- **Captura de Imagens**: O aplicativo Android permite a captura de imagens usando a câmera do dispositivo.
- **Análise de Imagens**: O backend processa as imagens enviadas, utilizando a Azure Cognitive Services para reconhecimento e contagem de objetos.
- **Integração de Serviços**: O sistema é projetado para se comunicar entre o aplicativo Android e o backend em Python, garantindo eficiência no processamento de dados.

## Instalação

### Requisitos

- Python 3.x
- Java 8 ou superior
- Android Studio
- Oracle Database (configuração em andamento)

### Configuração do Backend

1. Clone o repositório:
   ```bash
   git clone https://github.com/seuusuario/projeto-scanner.git
   cd projeto-scanner/PythonBackend
Instale as dependências:


pip install -r requirements.txt
Configure as variáveis de ambiente no seu sistema:


export AZURE_COGNITIVE_SERVICE_URL='sua_url_azure'
export AZURE_COGNITIVE_SERVICE_KEY='sua_chave_azure'
Inicie o servidor Flask:


python app.py
Configuração do Aplicativo Android
Abra o projeto no Android Studio.
Certifique-se de que todas as dependências no build.gradle estejam corretamente configuradas.
Configure as permissões necessárias para a câmera no AndroidManifest.xml.
Como Usar
Capture uma imagem através do aplicativo Android.
O aplicativo enviará a imagem para o backend.
O backend processará a imagem e retornará a contagem de objetos reconhecidos.
Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir uma "issue" ou enviar um "pull request".

Licença
Este projeto é licenciado sob a Licença MIT.

Contato
Se você tiver alguma dúvida ou sugestão, entre em contato:

Rodrigo Dias de Oliveira
rodrigo.digau@gmail.com


### Notas sobre o `README.md`

- **Personalização**: Substitua os valores de exemplo, como URLs e nomes, pelos detalhes reais do seu projeto.
- **Instruções de Uso**: Adapte a seção de como usar de acordo com as funcionalidades que você implementou.
- **Licença**: Inclua um arquivo de licença se necessário.
- **Contato**: rodrigo.digau@gmail.com, construindo sob supervisao de TRI MIND Ai Solutions.
![alt text](<t![_29990bc2-6621-4933-8f94-89450a130e08](https://github.com/user-attachments/assets/d20e7bea-3ec5-4edb-89f8-4b87f379ff1a)
ri mind ai solutions.jpg>)
