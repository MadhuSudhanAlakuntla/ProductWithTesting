package com.srvnn.ProductApplication.controller;

import com.srvnn.ProductApplication.model.RegisterRequest;
import com.srvnn.ProductApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        try {
            UUID uuid = userService.registerUser(request);
            return new ResponseEntity<>(uuid.toString(), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
