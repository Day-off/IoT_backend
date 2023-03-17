package com.example.iot_backend.service;

import com.example.iot_backend.dto.Report;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final RestTemplate restTemplate;

    public Report getResponse() {
        String url = "https://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
        String res = "";
        Report value = null;
        try {
            ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
            res = resp.getBody();
            XmlMapper xmlMapper = new XmlMapper();
            value = xmlMapper.readValue(res, Report.class);
        } catch (Exception e) {
            e.getCause();
        }
        return value;
    }

}
