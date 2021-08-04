package com.backend.demo.rest_controllers;

import com.backend.demo.VideoStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class SubmarineVideoStreamController {

    @Autowired
    VideoStreamingService service;
    public static final String VideoUploadingDir = "./data/Submarine"; //";

    @GetMapping(value = "/video_submarine", produces = "application/octet-stream")
    public ResponseEntity<ResourceRegion> getVideo(@RequestHeader(value = "Range", required = false) String rangeHeader)
            throws IOException {

        if (!new File(VideoUploadingDir).exists()) {
            new File(VideoUploadingDir).mkdirs();
        }
        return service.getVideoRegion(rangeHeader, VideoUploadingDir);

    }

}
