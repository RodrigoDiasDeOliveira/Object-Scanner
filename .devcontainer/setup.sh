#!/bin/bash

echo "Configuring Object-Scanner environment"

cd PythonBackend

python -m venv .venv

source .venv/bin/activate

pip install --upgrade pip

if [ -f requirements.txt ]; then
    pip install -r requirements.txt
fi

pip install ultralytics

echo "Environment ready"