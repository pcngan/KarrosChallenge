package com.example.demo.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import javax.persistence.*;
import java.util.List;

@Entity(name = "GPS_detail")
public class GPS {
    private @Id  @GeneratedValue
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private GPSMetadata metadata;

    @OneToMany(cascade = CascadeType.ALL)
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Waypoint> wpt;

    @OneToOne(cascade = CascadeType.ALL)
    private GPSTrack trk;

    public GPS(){}

    public GPS(GPSMetadata metadata, List<Waypoint> wpt, GPSTrack trk) {
        this.metadata = metadata;
        this.wpt = wpt;
        this.trk = trk;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GPSMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(GPSMetadata metadata) {
        this.metadata = metadata;
    }

    public List<Waypoint> getWpt() {
        return wpt;
    }

    public void setWpt(List<Waypoint> wpt) {
        this.wpt = wpt;
    }

    public GPSTrack getTrk() {
        return trk;
    }

    public void setTrk(GPSTrack trk) {
        this.trk = trk;
    }
}
