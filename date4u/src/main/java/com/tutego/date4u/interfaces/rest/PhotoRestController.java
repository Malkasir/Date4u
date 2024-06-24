package com.tutego.date4u.interfaces.rest;

import com.tutego.date4u.core.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PhotoRestController {

    private final PhotoService photoService;

    public PhotoRestController( PhotoService photos ) {
        this.photoService = photos;
    }

    @GetMapping(path = "/api/photos/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> photo(@PathVariable String name) {
        byte[] image = photoService.download(name).orElse(null);
        if (image != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "This photo is not available");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }
    }

}