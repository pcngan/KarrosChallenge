package com.example.demo.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import javax.persistence.*;
import java.util.List;

@Entity
public class GPSTrack {
    private @Id @GeneratedValue Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JacksonXmlElementWrapper(localName = "trkseg")
    private List<Trackpoint> trkpt;

    public GPSTrack(){}

    public GPSTrack(List<Trackpoint> trkpt) {
        this.trkpt = trkpt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Trackpoint> getTrkpt() {
        return trkpt;
    }

    public void setTrkpt(List<Trackpoint> trkpt) {
        this.trkpt = trkpt;
    }
}
