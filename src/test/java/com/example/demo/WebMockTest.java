package com.example.demo;

import com.example.demo.controller.GPSController;
import com.example.demo.domain.GPS;
import com.example.demo.domain.GPSMetadata;
import com.example.demo.service.GPSService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
public class WebMockTest {

    @Autowired
    private GPSController controller;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private GPSService service;

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        controller = new GPSController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getGPSbyID()throws Exception{
        GPS gps = new GPS();
        GPSMetadata metadata = new GPSMetadata("name","desc","author",new Date());
        gps.setMetadata(metadata);
        when(service.getDetailGPS(anyLong())).thenReturn(gps);
        mockMvc.perform(MockMvcRequestBuilders.get("/gps/10"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("<name>name</name>")));
    }

    @Test
    public void getAllGPS()throws Exception{
        ArrayList<GPSMetadata> results = new ArrayList<GPSMetadata>();
        GPSMetadata metadata = new GPSMetadata("name","desc","author",new Date());
        results.add(metadata);
        when(service.getAllGPS(anyInt(),anyInt())).thenReturn(results);
        mockMvc.perform(MockMvcRequestBuilders.get("/gps"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("<List><item><id/><name>name</name>")));
    }

    @Test
    public void UploadGPS()throws Exception{
        GPS gps = new GPS();
        GPSMetadata metadata = new GPSMetadata("name","desc","author",new Date());
        gps.setMetadata(metadata);
        MockMultipartFile xmlFile = new MockMultipartFile("test.gpx", "", "application/xml", "<GPS><metadata><id/><name>name</name><desc>desc</desc><author>author</author><time>1596672760783</time><link/></metadata><wpt/><trk/></GPS>".getBytes());

        // Mock Response
        when(service.upload(anyObject())).thenReturn(gps);
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
                .file("file", xmlFile.getBytes())
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(containsString("<name>name</name>")));
    }
}
