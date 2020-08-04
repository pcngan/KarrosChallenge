package com.example.demo;

import com.example.demo.service.GPSService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GPSService service;

    @Test
    public void getAllShouldOK() throws Exception
    {
        RequestBuilder request = MockMvcRequestBuilders.get("/gps");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals("<List/>", result.getResponse().getContentAsString());
    }

    @Test
    public void countGPS()throws Exception{
        Assert.assertEquals(new Long(0),service.countGPS());
    }

    @Test
    public void uploadGPS() throws Exception{
        FileInputStream input = new FileInputStream("sample\\sample.gpx");
        Assert.assertNotNull(input);
        MockMultipartFile upload= new MockMultipartFile("file",input);
        mockMvc.perform(fileUpload("/upload")
                        .file("file",upload.getBytes())).andReturn();
    }

    @Test
    public void getGPSDetail()throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/gps/1")).andReturn();
//        assertEquals("<List/>", result.getResponse().getContentAsString());
    }
}
