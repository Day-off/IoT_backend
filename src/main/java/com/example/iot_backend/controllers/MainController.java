package com.example.iot_backend.controllers;


import com.example.iot_backend.dto.Report;
import com.example.iot_backend.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MainController {

    private final ApiService apiService;

    @GetMapping(value = "getGreeting")
    public Report getGreeting() {
        return apiService.getResponse();
    }
}
