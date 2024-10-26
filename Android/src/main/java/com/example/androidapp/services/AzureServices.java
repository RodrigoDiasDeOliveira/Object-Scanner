package com.example.androidapp.services;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;

public class AzureService {
    private static final String BACKEND_URL = "http://<backend_url>/scan";

    public void sendImageToBackend(Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(BACKEND_URL).build();
        client.newCall(request).enqueue(callback);
    }
}
