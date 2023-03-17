package com.example.iot_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Night {

    @JacksonXmlProperty(localName = "phenomenon")
    private String phenomenon;

    @JacksonXmlProperty(localName = "tempmin")
    private int tempmin;

    @JacksonXmlProperty(localName = "tempmax")
    private int tempmax;

    @JacksonXmlProperty(localName = "text")
    private String text;

    @JacksonXmlProperty(localName = "place")
    @JacksonXmlCData
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Place> placeList;

    @JacksonXmlProperty(localName = "wind")
    @JacksonXmlCData
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Wind> windList;

    @JacksonXmlProperty(localName = "sea")
    private String sea;

    @JacksonXmlProperty(localName = "peipsi")
    private String peipsi;

}