package com.example.androidapp.service;

import com.example.androidapp.model.ObjectCount;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ObjectScanService {

    private final String backendUrl = "http://your-backend-url/api/process";

    public ObjectCount processImage(byte[] image) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(backendUrl, image, ObjectCount.class);
    }
}
