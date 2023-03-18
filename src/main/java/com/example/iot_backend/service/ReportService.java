package com.example.iot_backend.service;

import com.example.iot_backend.dto.Place;
import com.example.iot_backend.dto.Report;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
    private Report report;
    private float averageMax;
    private float averageMin;

    public Report getFullReport() {
        return report;
    }

    public void makeFullReport(){
        try {
            ResponseEntity<String> resp = restTemplate.getForEntity(BASE_URL, String.class);
            String res = resp.getBody();
            XmlMapper xmlMapper = new XmlMapper();
            report = xmlMapper.readValue(res, Report.class);
        } catch (NullPointerException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<String> getAverageTemperature() {
        return ResponseEntity.ok("MAX: "+ String.format("%.2f", averageMax)+" MIN: "+ String.format("%.2f", averageMin));
    }

    @Scheduled(fixedRate=60*60*1000)
    public void countAverageTemperature() {
        makeFullReport();
        averageMax = countAverageMax(report.getForecastList().get(0).getDay().getPlaceList());
        averageMin = countAverageMin(report.getForecastList().get(0).getNight().getPlaceList());
    }

    private float countAverageMin(List<Place> cities) {
        float sumTemperature = 0;
        for(Place city: cities){
            sumTemperature += Integer.parseInt(city.getTempmin());
        }
        return sumTemperature/cities.size();
    }

    private float countAverageMax(List<Place> cities) {
        float sumTemperature = 0;
        for(Place city: cities){
            sumTemperature += Integer.parseInt(city.getTempmax());
        }
        return sumTemperature/cities.size();
    }
}
