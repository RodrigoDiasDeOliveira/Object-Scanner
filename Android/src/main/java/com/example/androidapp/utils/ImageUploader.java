package com.seu_pacote.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageUploader {

    private static final String TAG = "ImageUploader";

    public static File saveImageToInternalStorage(Context context, Uri imageUri) {
        File directory = context.getFilesDir();
        File imageFile = new File(directory, "uploaded_image.jpg");

        try (InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
             OutputStream outputStream = new FileOutputStream(imageFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            Log.d(TAG, "Imagem salva: " + imageFile.getAbsolutePath());
            return imageFile;
        } catch (Exception e) {
            Log.e(TAG, "Erro ao salvar imagem", e);
            return null;
        }
    }
}
