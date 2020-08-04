package com.example.demo.service;

import com.example.demo.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.List;

@Service
public class GPSService {
    @Autowired
    GPSMetadataRepository metaRepository;
    @Autowired
    GPSRepository repository;
    private static final Logger log = LoggerFactory.getLogger(GPSService.class);

    private  static String COLUMN_NAME = "time";

    public void upload(MultipartFile file) throws IOException{
        log.info("upload Start");
        String xml = inputStreamToString(file.getInputStream());
        GPS value = convertStringtoGPS(xml);
        log.info("upload End " + value.getMetadata().getName());
        repository.save(value);
    }

    private String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    private GPS convertStringtoGPS(String xmlGPS) throws IOException{
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        GPS value = xmlMapper.readValue(xmlGPS, GPS.class);
        return value;
    }

    public void addGPS(GPS gps){
        repository.save(gps);
    }

    public List<GPSMetadata> getAllGPS(Integer pageNo, Integer pageSize){
        Pageable page = new PageRequest(pageNo, pageSize, new Sort(Sort.Direction.DESC,COLUMN_NAME) );
        return metaRepository.findAll(page).getContent();
    }

    public GPS getDetailGPS(Long id){
        List<GPS> GPSs= repository.filterByMetaId(id);
        return GPSs.isEmpty()?null: GPSs.get(0);
    }

    public Long countGPS() {
        return repository.count();
    }
}
