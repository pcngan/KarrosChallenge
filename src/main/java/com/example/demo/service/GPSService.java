package com.example.demo.service;

import com.example.demo.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.List;

@Service
public interface GPSService {

    String COLUMN_NAME = "time";

    GPS upload(MultipartFile file) throws IOException;

    GPS addGPS(GPS gps);

    List<GPSMetadata> getAllGPS(Integer pageNo, Integer pageSize);

    public GPS getDetailGPS(Long id);

    Long countGPS();
}
