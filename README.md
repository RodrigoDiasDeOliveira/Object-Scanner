# Object-Scanner
![detector](https://github.com/user-attachments/assets/37645298-c6ae-4051-8510-b6c02c6d69dc)

Este projeto visa desenvolver um scanner de objetos com IA para contagem automática de materiais em ambientes industriais. Utiliza Java para Android, Spring Boot, um backend em Python com Azure Cognitive Services e Oracle para armazenamento.
Estrutura basica inicial

object-scanner-ai/
├── README.md                           # Descrição geral do projeto e instruções
├── AndroidApp/                         # Código do app Android em Java com Spring Boot
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/androidapp/
│   │   │   ├── resources/
│   │   │   └── AndroidManifest.xml
│   └── build.gradle                    # Configurações Gradle do app
├── PythonBackend/                      # Backend em Python para processamento de IA
│   ├── app.py                          # Arquivo principal do Flask
│   ├── services/
│   ├── utils/
│   ├── config/
│   └── requirements.txt                # Dependências do backend Python
├── OracleDatabase/                     # Scripts e configuração do banco de dados Oracle
│   ├── schema/
│   ├── migrations/
│   └── config/
└── docs/                               # Documentação adicional
    └── architecture.md                 # Arquitetura detalhada do projeto

    
