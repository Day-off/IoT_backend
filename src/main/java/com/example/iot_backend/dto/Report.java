package com.example.iot_backend.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Report {

    @JacksonXmlProperty(localName = "forecast")
    @JacksonXmlCData
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Forecast> forecastList;
}