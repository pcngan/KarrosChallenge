package com.example.demo.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@JacksonXmlRootElement(localName = "trkpt")
public class Trackpoint {
    private @Id @GeneratedValue Long id;
    @JacksonXmlProperty(isAttribute = true)
    private Double lat;
    @JacksonXmlProperty(isAttribute = true)
    private Double lon;
    private Double ele;
    private Date time;

    public Trackpoint(){}

    public Trackpoint(Double lat, Double lon, Double ele, Date time) {
        this.lat = lat;
        this.lon = lon;
        this.ele = ele;
        this.time = time;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public Double getEle() {
        return ele;
    }

    public Date getTime() {
        return time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public void setEle(Double ele) {
        this.ele = ele;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
