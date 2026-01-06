package com.example.demo.controller;

import com.example.demo.AnalyticalRepository;
import com.example.demo.Analytics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnalyticsController {
    AnalyticalRepository analyticalRepository;
    public AnalyticsController(AnalyticalRepository analyticalRepository) {
        this.analyticalRepository = analyticalRepository;
    }
    @GetMapping("/analytics")
    public Analytics getAnalytics(){
        return analyticalRepository.getAnalytics();
    }
}
