package com.example.demo.controller;

import com.example.demo.domain.GPS;
import com.example.demo.domain.GPSMetadata;
import com.example.demo.domain.GPSRepository;
import com.example.demo.service.GPSService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GPSController {
    private final GPSService service;

    public GPSController(GPSService service) {
        this.service = service;
    }

    @PostMapping(path = "/gps",produces = MediaType.APPLICATION_XML_VALUE)
    void addGPS(@RequestBody GPS newGPS){
        service.addGPS(newGPS);
    }

    @GetMapping(path = "/gps", produces = MediaType.APPLICATION_XML_VALUE)
    List<GPSMetadata> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                             @RequestParam(defaultValue = "10") Integer pageSize){
        return service.getAllGPS(pageNo, pageSize);
    }

    @GetMapping(path = "/gps/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    GPS getDetail(@PathVariable Long id){
        return service.getDetailGPS(id);
    }

    @PostMapping(path = "/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file)throws IOException {
        service.upload(file);
    }
}
