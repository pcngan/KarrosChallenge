package com.example.demo.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Waypoint{
    private @Id @GeneratedValue Long id;

    @JacksonXmlProperty(isAttribute = true)
    private Double lat;

    @JacksonXmlProperty(isAttribute = true)
    private Double lon;

    private String name;
    private String sym;

    public Waypoint(){}

    public Waypoint(Double lat, Double lon, String name, String sym) {
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.sym = sym;
    }

    public Double getLat(){return lat;}

    public Double getLon(){return lon;}

    public String getName(){return name;}

    public String getSym(){return sym;}

    public void setId(Long id){this.id=id;}

    public void setLat(Double lat){this.lat = lat;}

    public void setLon(Double lon){this.lon = lon;}

    public void setName(String name){this.name=name;}

    public void setSym(String sym){this.sym=sym;}
}
