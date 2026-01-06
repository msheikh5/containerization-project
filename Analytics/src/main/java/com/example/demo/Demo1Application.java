package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
@EnableScheduling
@SpringBootApplication
public class Demo1Application {
    @Scheduled(fixedRateString = "${fixedRate}")
    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

}
