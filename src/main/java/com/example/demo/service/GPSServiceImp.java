package com.example.demo.service;

import com.example.demo.domain.GPS;
import com.example.demo.domain.GPSMetadata;
import com.example.demo.repository.GPSMetadataRepository;
import com.example.demo.repository.GPSRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class GPSServiceImp implements GPSService{

    private GPSMetadataRepository metaRepository;

    private GPSRepository repository;

    @Autowired
    public GPSServiceImp(GPSMetadataRepository metaRepository, GPSRepository repository) {
        this.metaRepository = metaRepository;
        this.repository = repository;
    }

    @Override
    public GPS upload(MultipartFile file) throws IOException {
        String xml = inputStreamToString(file.getInputStream());
        GPS value = convertStringtoGPS(xml);
        return repository.save(value);
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

    @Override
    public GPS addGPS(GPS gps){
        return repository.save(gps);
    }

    @Override
    public List<GPSMetadata> getAllGPS(Integer pageNo, Integer pageSize){
        Pageable page = new PageRequest(pageNo, pageSize, new Sort(Sort.Direction.DESC,COLUMN_NAME) );
        return metaRepository.findAll(page).getContent();
    }

    @Override
    public GPS getDetailGPS(Long id){
        List<GPS> GPSs= repository.filterByMetaId(id);
        return GPSs.isEmpty()?null: GPSs.get(0);
    }

    @Override
    public Long countGPS() {
        return repository.count();
    }
}
