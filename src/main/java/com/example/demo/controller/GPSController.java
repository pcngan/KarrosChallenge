package com.example.demo.controller;

import com.example.demo.domain.GPS;
import com.example.demo.domain.GPSMetadata;
import com.example.demo.service.GPSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class GPSController {

    private final GPSService service;

    @Autowired
    public GPSController(GPSService service) {
        this.service = service;
    }

//    @PostMapping(path = "/gps",produces = MediaType.APPLICATION_XML_VALUE)
//    ResponseEntity<GPS> addGPS(@RequestBody GPS newGPS){
//        return new ResponseEntity<>(service.addGPS(newGPS), HttpStatus.CREATED);
//    }

    @GetMapping(path = "/gps")
    ResponseEntity<List<GPSMetadata>> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                             @RequestParam(defaultValue = "10") Integer pageSize){
        return new ResponseEntity<>(service.getAllGPS(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping(path = "/gps/{id}")
    ResponseEntity<GPS> getDetail(@PathVariable Long id){
        return new ResponseEntity<>(service.getDetailGPS(id), HttpStatus.OK);
    }

    @PostMapping(path = "/upload")
    ResponseEntity<GPS> uploadFile(@RequestParam("file") MultipartFile file)throws IOException {
        return new ResponseEntity<>(service.upload(file), HttpStatus.CREATED);
    }
}
