package com.example.showdata;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class showDataController {
    showDataRepository showDataRepository;

    public showDataController(showDataRepository showDataRepository){
        this.showDataRepository=showDataRepository;
    }

    @GetMapping("/{user}/{password}/showdata")
    public List sendData(@PathVariable String user, @PathVariable String password) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange("http://host.docker.internal:8090/" + user + "/" + password, HttpMethod.GET, null, String.class);
        if (responseEntity.getBody().equals("accepted")) {
            return showDataRepository.findAll();
        } else
        {
            ArrayList arrayList =new ArrayList<>();
            arrayList.add("you dont have a permission to see a data");
            return arrayList;
        }
    }
}
