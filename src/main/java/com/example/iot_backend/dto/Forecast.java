package com.example.iot_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "forecast")
public class Forecast {

    @JacksonXmlProperty(isAttribute = true)
    @JacksonXmlCData
    private String date;

    @JacksonXmlProperty(localName = "night")
    @JacksonXmlCData
    private Night night;

    @JacksonXmlProperty(localName = "day")
    @JacksonXmlCData
    private Day day;

}
