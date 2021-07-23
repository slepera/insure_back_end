package com.backend.demo.rest_controllers;

import java.io.File;
import java.io.IOException;

import com.backend.demo.VideoStreamingService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.support.ResourceRegion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoStreamController {

    @Autowired
    VideoStreamingService service;
    public static final String VideoUploadingDir = "./data/Platform"; //";

    @GetMapping(value = "/video", produces = "application/octet-stream")
    public ResponseEntity<ResourceRegion> getVideo(@RequestHeader(value = "Range", required = false) String rangeHeader)
            throws IOException {

        if (!new File(VideoUploadingDir).exists()) {
            new File(VideoUploadingDir).mkdirs();
        }
        return service.getVideoRegion(rangeHeader, VideoUploadingDir);

    }

}
