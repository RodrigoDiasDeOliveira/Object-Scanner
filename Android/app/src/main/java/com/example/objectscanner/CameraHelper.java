package com.example.objectscanner;

import android.content.Context;
import android.util.Log;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CameraHelper {

    private static final String TAG = "CameraHelper";

    private final Context context;
    private final PreviewView previewView;
    private final LifecycleOwner lifecycleOwner;

    private Camera camera;
    private CameraSelector cameraSelector;
    private ProcessCameraProvider cameraProvider;
    private ExecutorService cameraExecutor;

    public CameraHelper(Context context, PreviewView previewView, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.previewView = previewView;
        this.lifecycleOwner = lifecycleOwner;
        this.cameraExecutor = Executors.newSingleThreadExecutor();
        this.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
    }

    /**
     * Inicia a câmera (método principal)
     */
    public void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(context);

        cameraProviderFuture.addListener(() -> {
            try {
                cameraProvider = cameraProviderFuture.get();

                // Preview
                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                // Seleciona a câmera
                camera = cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview
                );

                Log.d(TAG, "Câmera iniciada com sucesso");

            } catch (Exception e) {
                Log.e(TAG, "Erro ao iniciar câmera", e);
            }
        }, ContextCompat.getMainExecutor(context));
    }

    /**
     * Alterna entre câmera frontal e traseira
     */
    public void switchCamera() {
        if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
            cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA;
        } else {
            cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
        }

        // Reinicia a câmera com o novo selector
        if (cameraProvider != null) {
            cameraProvider.unbindAll();
            startCamera();
        }
    }

    /**
     * Libera recursos da câmera
     */
    public void shutdown() {
        if (cameraExecutor != null) {
            cameraExecutor.shutdown();
        }
        if (cameraProvider != null) {
            cameraProvider.unbindAll();
        }
    }

    public boolean isUsingBackCamera() {
        return cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA;
    }
}
