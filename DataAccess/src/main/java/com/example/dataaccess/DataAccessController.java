package com.example.dataaccess;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class DataAccessController  {
    DataAccessRepository dataAccessRepository;


    DataAccessController(DataAccessRepository dataAccessRepository){
        this.dataAccessRepository=dataAccessRepository;


    }

    @PostMapping("/{user}/{password}/{id}/{name}/{mark}")
    public String addToDatabase(@PathVariable String user,@PathVariable String password,@PathVariable int id, @PathVariable String name,@PathVariable int mark){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity responseEntity=restTemplate.exchange("http://host.docker.internal:8090/"+user+"/"+password, HttpMethod.GET,null,String.class);
        if (responseEntity.getBody().equals("accepted"))
        {
            dataAccessRepository.addToDatabase(id, name, mark);
            return "we add new data to mySql database ";
        }
        else return "we dont have access to add to database";

    }
}
