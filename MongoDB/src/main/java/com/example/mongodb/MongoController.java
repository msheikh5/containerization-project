package com.example.mongodb;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MongoController {

    MongoRepository mongoRepository;
    Analytics analytics;
    public MongoController(MongoRepository mongoRepository){
        this.mongoRepository=mongoRepository;
    }
    @GetMapping("/senddata")
    public String sendData(){
        RestTemplate restTemplate=new RestTemplate();
        analytics=restTemplate.getForObject("http://host.docker.internal:8070/analytics",Analytics.class);
        mongoRepository.deleteAll();
        mongoRepository.save(analytics);
        return ("data sent to mongodb database ");


    }
}
