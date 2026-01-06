package com.example.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class MongoDbApplication {
    @Scheduled(fixedRateString = "${fixedRate}")
    public static void main(String[] args) {SpringApplication.run(MongoDbApplication.class, args);
    }

}
