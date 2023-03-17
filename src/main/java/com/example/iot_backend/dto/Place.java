package com.example.iot_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Place {
    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "phenomenon")
    private String phenomenon;

    @JacksonXmlProperty(localName = "tempmin")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int tempmin;

    @JacksonXmlProperty(localName = "tempmax")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int tempmax;
}
