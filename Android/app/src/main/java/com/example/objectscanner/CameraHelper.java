package com.example.objectscanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
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

    private ImageCapture imageCapture;

    private final ExecutorService cameraExecutor;



    public CameraHelper(
            Context context,
            PreviewView previewView,
            LifecycleOwner lifecycleOwner
    ) {

        this.context = context;
        this.previewView = previewView;
        this.lifecycleOwner = lifecycleOwner;

        this.cameraExecutor =
                Executors.newSingleThreadExecutor();

        this.cameraSelector =
                CameraSelector.DEFAULT_BACK_CAMERA;
    }




    /**
     * Compatibilidade com MainActivity
     */
    public void startCamera(boolean useBackCamera){

        cameraSelector =
                useBackCamera
                ? CameraSelector.DEFAULT_BACK_CAMERA
                : CameraSelector.DEFAULT_FRONT_CAMERA;


        startCamera();
    }

    /**
     * Inicializa câmera
    */
   public void startCamera(){


        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(context);

        cameraProviderFuture.addListener(() -> {

            try {
                cameraProvider =
                        cameraProviderFuture.get();

                Preview preview =
                        new Preview.Builder()
                        .build();

                preview.setSurfaceProvider(
                        previewView.getSurfaceProvider()
                );

               imageCapture =
                        new ImageCapture.Builder()
                      .build();

                cameraProvider.unbindAll();



                camera =
                        cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview,
                                imageCapture
                        );



                Log.d(
                        TAG,
                        "Camera iniciada com sucesso"
                );



            } catch(Exception e){

                Log.e(
                        TAG,
                        "Erro ao iniciar camera",
                        e
                );

            }


        },
        ContextCompat.getMainExecutor(context));

    }

    /**
     * Compatibilidade com MainActivity
     */
    public void switchCamera(boolean useBackCamera){
        cameraSelector =
                useBackCamera
                ? CameraSelector.DEFAULT_BACK_CAMERA
                : CameraSelector.DEFAULT_FRONT_CAMERA;

        if(cameraProvider != null){

            cameraProvider.unbindAll();

            startCamera();

        }

    }


    /**
     * Captura foto
     */
    public void takePhoto(PhotoCallback callback){

        if(imageCapture == null){

            Log.e(TAG,"ImageCapture não inicializado");

            return;
        }


        imageCapture.takePicture(
                cameraExecutor,

                new ImageCapture.OnImageCapturedCallback(){

                    @Override
                    public void onCaptureSuccess(
                            ImageProxy image
                    ){


                        Bitmap bitmap =
                                previewView.getBitmap();


                        image.close();

                        if(bitmap != null){

                            callback.onPhotoCaptured(bitmap);

                        }

                    }


                    @Override
                    public void onError(
                            ImageCaptureException exception
                    ){

                        Log.e(
                                TAG,
                                "Erro captura imagem",
                                exception
                        );

                    }

                }

        );


    }





    public interface PhotoCallback{

        void onPhotoCaptured(Bitmap bitmap);

    }

    /**
     * Libera recursos
     */
    public void shutdown(){

        if(cameraExecutor != null){

            cameraExecutor.shutdown();

        }

     if(cameraProvider != null){

            cameraProvider.unbindAll();

        }

    }

    public boolean isUsingBackCamera(){

        return cameraSelector ==
                CameraSelector.DEFAULT_BACK_CAMERA;

    }

}
