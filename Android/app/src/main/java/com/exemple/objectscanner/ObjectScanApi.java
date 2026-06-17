package com.example.objectscanner;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ObjectScanApi {
    @Multipart
    @POST("/detect")
    Call<ObjectCountResponse> detectObjects(@Part MultipartBody.Part image);
}