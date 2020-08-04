package com.example.demo.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MetaLink {
    private @Id @GeneratedValue Long id;
    @JacksonXmlProperty(isAttribute = true)
    private String href;
    private String text;

    public MetaLink(){}

    public MetaLink(String href, String text) {
        this.href = href;
        this.text = text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
