package com.example.androidapp.controller;

import com.example.androidapp.model.ObjectCount;
import com.example.androidapp.service.ObjectScanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scan")
public class ObjectScanController {

    private final ObjectScanService objectScanService;

    @Autowired
    public ObjectScanController(ObjectScanService objectScanService) {
        this.objectScanService = objectScanService;
    }

    @PostMapping("/upload")
    public ObjectCount uploadImage(@RequestParam("image") byte[] image) {
        return objectScanService.processImage(image);
    }
}
