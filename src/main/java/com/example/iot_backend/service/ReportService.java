package com.example.iot_backend.service;

import com.example.iot_backend.dto.Report;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
    private Report report;

    public Report getFullReport() {
        try {
            ResponseEntity<String> resp = restTemplate.getForEntity(BASE_URL, String.class);
            String res = resp.getBody();
            XmlMapper xmlMapper = new XmlMapper();
            report = xmlMapper.readValue(res, Report.class);
        } catch (NullPointerException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return report;
    }

    public ResponseEntity<String> getAverageTemperature() {
        return ResponseEntity.ok("");
    }
}
