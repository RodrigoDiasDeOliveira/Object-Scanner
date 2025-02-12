package com.example.androidapp.tensorflow;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import org.tensorflow.lite.task.vision.detector.ObjectDetector;
import org.tensorflow.lite.task.vision.detector.Detection;
import java.io.IOException;
import java.util.List;

public class ObjectDetector {
    private ObjectDetector detector;

    public ObjectDetector(Context context) {
        try {
            detector = ObjectDetector.createFromFileAndOptions(
                    context,
                    "model.tflite",  // 🔹 O modelo treinado deve estar em assets/
                    new ObjectDetector.ObjectDetectorOptions.Builder()
                            .setMaxResults(3)  // 🔹 Máximo de 3 objetos detectados por imagem
                            .setScoreThreshold(0.5f)  // 🔹 Confiança mínima de 50%
                            .build()
            );
        } catch (IOException e) {
            Log.e("ObjectDetector", "Erro ao carregar modelo TensorFlow", e);
        }
    }

    public String detectObjects(Bitmap bitmap) {
        if (detector == null) {
            return "Modelo não carregado!";
        }

        List<Detection> results = detector.detect(bitmap);
        StringBuilder detectedObjects = new StringBuilder();

        for (Detection detection : results) {
            detectedObjects.append(detection.getCategories().get(0).getLabel()).append(", ");
        }

        return detectedObjects.toString();
    }
}
