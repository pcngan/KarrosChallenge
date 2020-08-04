package com.example.demo.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import javax.persistence.*;
import java.util.Date;

@Entity
public class GPSMetadata {
    private @Id @GeneratedValue Long id;

    private String name;
    @Column(length = 1000)
    private String desc;
    private String author;
    private Date time;
    @OneToOne(cascade = CascadeType.ALL)
    private MetaLink link;

    public GPSMetadata(){}

    public GPSMetadata( String name, String desc, String author, Date time) {
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.time = time;
    }

    public GPSMetadata(String name, Date time) {
        this.name = name;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getAuthor() {
        return author;
    }

    public Date getTime() {
        return time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public MetaLink getLink() {
        return link;
    }

    public void setLink(MetaLink link) {
        this.link = link;
    }
}
