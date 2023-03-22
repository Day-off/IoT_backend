package com.example.iot_backend.service;

import com.example.iot_backend.dto.Place;
import com.example.iot_backend.dto.Report;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
    private Report report;
    private float averageMax;
    private float averageMin;

    public Report getFullReport() {
        makeFullReport();
        return report;
    }

    public void makeFullReport() {
        try {
            ResponseEntity<String> resp = restTemplate.getForEntity(BASE_URL, String.class);
            String res = resp.getBody();
            XmlMapper xmlMapper = new XmlMapper();
            report = xmlMapper.readValue(res, Report.class);
        } catch (NullPointerException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return sdfDate.format(now);
    }

    public String getAverageTemperature() {
        return "MAX-TEMP: " + String.format("%.2f", averageMax)
                + ", MIN-TEMP: " + String.format("%.2f", averageMin)
                + ", UPDATED: " + getCurrentTimeStamp();
    }

    @Scheduled(fixedRate = 60*60*1000)
    public void recordAverageTemperatureToFile() {
        makeFullReport();
        averageMax = countAverageMax(report.getForecastList().get(0).getDay().getPlaceList());
        averageMin = countAverageMin(report.getForecastList().get(0).getNight().getPlaceList());
        recordToFile();
    }

    private void recordToFile() {
        File reportFile = new File("temperatureReport.txt");
        if (reportFile.exists()) {
            updateFile(reportFile);
        } else {
            createFile(reportFile);
        }
    }

    private void updateFile(File reportFile) {
        try{
            Writer updatedReport = new BufferedWriter(new FileWriter(reportFile.getName(), true));
            updatedReport.write(getAverageTemperature()+"\n");
            updatedReport.close();
            log.info("Successfully wrote to the updated file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void createFile(File reportFile) {
        try {
            FileWriter myWriter = new FileWriter(reportFile.getName());
            myWriter.write(getAverageTemperature() + "\n");
            myWriter.close();
            log.info("Successfully wrote to the file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private float countAverageMin(List<Place> cities) {
        float sumTemperature = 0;
        for (Place city : cities) {
            sumTemperature += Integer.parseInt(city.getTempmin());
        }
        return sumTemperature / cities.size();
    }

    private float countAverageMax(List<Place> cities) {
        float sumTemperature = 0;
        for (Place city : cities) {
            sumTemperature += Integer.parseInt(city.getTempmax());
        }
        return sumTemperature / cities.size();
    }
}
