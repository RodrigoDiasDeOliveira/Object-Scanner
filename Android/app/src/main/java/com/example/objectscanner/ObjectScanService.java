package com.example.objectscanner;

import android.graphics.Bitmap;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.ByteArrayOutputStream;

public class ObjectScanService {

    private final ObjectScanApi api;

    public ObjectScanService() {
        api = RetrofitClient.getClient().create(ObjectScanApi.class);
    }

    public void sendImageToServer(Bitmap bitmap, ObjectScanCallback callback) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[] byteArray = stream.toByteArray();

      RequestBody requestFile = RequestBody.create(
        MediaType.parse("image/jpeg"),
        byteArray);
  
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", "photo.jpg", requestFile);

        Call<ObjectCountResponse> call = api.detectObjects(body);
        call.enqueue(new Callback<ObjectCountResponse>() {
            @Override
            public void onResponse(Call<ObjectCountResponse> call, Response<ObjectCountResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResult(response.body());
                } else {
                    callback.onResult(null);
                }
            }

            @Override
            public void onFailure(Call<ObjectCountResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onResult(null);
            }
        });
    }

    public interface ObjectScanCallback {
        void onResult(ObjectCountResponse response);
    }
}
