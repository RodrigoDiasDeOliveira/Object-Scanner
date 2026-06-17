package com.example.objectscanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.core.content.ContextCompat;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class CameraHelper {
    private static final String TAG = "CameraHelper";
    
    private final Context context;
    private final PreviewView previewView;
    private ImageCapture imageCapture;
    private ExecutorService cameraExecutor;

    public CameraHelper(Context context, PreviewView previewView) {
        this.context = context;
        this.previewView = previewView;
        this.cameraExecutor = Executors.newSingleThreadExecutor();
    }

    // ... (mantenha o método startCamera e switchCamera iguais da versão anterior)

    public void takePhoto(Consumer<Bitmap> callback) {
        if (imageCapture == null) return;

        imageCapture.takePicture(ContextCompat.getMainExecutor(context),
            new ImageCapture.OnImageCapturedCallback() {
                @Override
                public void onCaptureSuccess(@NonNull ImageProxy image) {
                    Bitmap bitmap = imageProxyToBitmap(image);
                    image.close();
                    if (bitmap != null) {
                        callback.accept(bitmap);
                    }
                }

                @Override
                public void onError(@NonNull ImageCaptureException exception) {
                    Log.e(TAG, "Erro ao capturar foto", exception);
                    callback.accept(null);
                }
            });
    }

    private Bitmap imageProxyToBitmap(ImageProxy image) {
        ImageProxy.PlaneProxy plane = image.getPlanes()[0];
        ByteBuffer buffer = plane.getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);

        Bitmap bitmap = android.graphics.BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        
        // Correção de rotação
        Matrix matrix = new Matrix();
        matrix.postRotate(image.getImageInfo().getRotationDegrees());
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public void shutdown() {
        if (cameraExecutor != null) {
            cameraExecutor.shutdown();
        }
    }
}