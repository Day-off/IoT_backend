package com.example.iot_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
@Getter
@Setter
public class Wind {
    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "direction")
    private String direction;

    @JacksonXmlProperty(localName = "speedmin")
    private int speedmin;
    @JacksonXmlProperty(localName = "speedmax")
    private int speedmax;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String gust;

}
