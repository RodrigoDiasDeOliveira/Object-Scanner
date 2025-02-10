package com.example.androidapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidapp.service.ObjectScanService;
import com.example.androidapp.tensorflow.ObjectDetector;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ObjectScanService objectScanService;
    private ImageView imageView;
    private ObjectDetector objectDetector;  // Novo: Classe para integrar TensorFlow Lite

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objectScanService = new ObjectScanService();
        objectDetector = new ObjectDetector(this);  // Inicializar TensorFlow Lite

        Button captureButton = findViewById(R.id.captureButton);
        imageView = findViewById(R.id.imageView);

        captureButton.setOnClickListener(v -> dispatchTakePictureIntent());
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);

            // 🔹 Detectar objetos na imagem antes de enviá-la ao backend
            String detectedObjects = objectDetector.detectObjects(imageBitmap);
            Log.d("MainActivity", "Objetos detectados: " + detectedObjects);
            Toast.makeText(this, "Objetos: " + detectedObjects, Toast.LENGTH_SHORT).show();

            // 🔹 Enviar imagem ao backend após processamento
            sendImageToBackend(imageBitmap);
        }
    }

    private void sendImageToBackend(Bitmap bitmap) {
        try {
            File imageFile = new File(getCacheDir(), "captured_image.jpg");
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

            objectScanService.uploadImage(imageFile, new ObjectScanService.UploadCallback() {
                @Override
                public void onSuccess(String response) {
                    Log.d("MainActivity", "Resposta do backend: " + response);
                    Toast.makeText(MainActivity.this, "Imagem enviada!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Exception e) {
                    Log.e("MainActivity", "Erro ao enviar imagem", e);
                    Toast.makeText(MainActivity.this, "Erro ao enviar", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            Log.e("MainActivity", "Erro ao salvar imagem", e);
        }
    }
}
