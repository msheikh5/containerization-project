package com.example.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateUsersController {
    @GetMapping("/{user}/{password}")
    public ResponseEntity<String> check(@PathVariable  String user, @PathVariable String password){
        if (user.equals("admin")&& password.equals("admin"))
            return new ResponseEntity<>("accepted", HttpStatus.ACCEPTED);
        else return new ResponseEntity<>("access denied",HttpStatus.ACCEPTED);
    }
}
