package com.example.iot_backend.controllers;


import com.example.iot_backend.dto.Report;
import com.example.iot_backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReportController {

    private final ReportService reportService;

    @GetMapping(value = "getFullReport")
    public Report getFullReport() {
        return reportService.getFullReport();
    }

    @GetMapping(value = "getAverageTemperature")
    public ResponseEntity<String> getAverageTemperature() {
        return ResponseEntity.ok(reportService.getAverageTemperature());
    }


}
