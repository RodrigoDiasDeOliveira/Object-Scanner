package com.example.objectscanner;

import com.example.objectscanner.CameraHelper;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private PreviewView previewView;
    private ImageView imageResult;
    private TextView tvResults;
    private Button btnCapture, btnSwitchCamera;
    private ProgressBar progressBar;

    private CameraHelper cameraHelper;
    private ObjectScanService scanService;
    private boolean isUsingBackCamera = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        checkPermissions();

        cameraHelper = new CameraHelper(this, previewView,this);
        scanService = new ObjectScanService();
    }

    private void initViews() {
        previewView = findViewById(R.id.previewView);
        imageResult = findViewById(R.id.imageResult);
        tvResults = findViewById(R.id.tvResults);
        btnCapture = findViewById(R.id.btnCapture);
        btnSwitchCamera = findViewById(R.id.btnSwitchCamera);
        progressBar = findViewById(R.id.progressBar);

        btnCapture.setOnClickListener(v -> captureAndSend());
        btnSwitchCamera.setOnClickListener(v -> {
            isUsingBackCamera = !isUsingBackCamera;
            cameraHelper.switchCamera(isUsingBackCamera);
        });
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) 
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, 
                new String[]{Manifest.permission.CAMERA}, 101);
        } else {
            cameraHelper.startCamera(isUsingBackCamera);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, 
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101 && grantResults.length > 0 
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            cameraHelper.startCamera(isUsingBackCamera);
        } else {
            Toast.makeText(this, "Permissão da câmera é obrigatória", Toast.LENGTH_LONG).show();
        }
    }

    private void captureAndSend() {
        setLoading(true);
        tvResults.setText("");

        cameraHelper.takePhoto(bitmap -> {
            if (bitmap == null) {
                setLoading(false);
                Toast.makeText(this, getString(R.string.error_no_image), Toast.LENGTH_SHORT).show();
                return;
            }

            imageResult.setImageBitmap(bitmap);

            scanService.sendImageToServer(bitmap, response -> {
                runOnUiThread(() -> {
                    setLoading(false);
                    showResults(response);
                });
            });
        });
    }

    private void showResults(ObjectCountResponse response) {
        if (response != null && response.isSuccess()) {
            StringBuilder sb = new StringBuilder(getString(R.string.success) + "\n\n");
            if (response.getCounts() != null) {
                response.getCounts().forEach((cls, qty) -> 
                    sb.append("• ").append(cls).append(": ").append(qty).append(" unidade(s)\n"));
            }
            if (response.getSource() != null) {
                sb.append("\nFonte: ").append(response.getSource().toUpperCase());
            }
            tvResults.setText(sb.toString());
        } else {
            tvResults.setText(getString(R.string.error_server));
        }
    }

    private void setLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        btnCapture.setEnabled(!isLoading);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraHelper != null) {
            cameraHelper.shutdown();
        }
    }
}
