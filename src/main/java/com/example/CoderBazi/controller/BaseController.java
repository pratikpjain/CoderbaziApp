package com.example.CoderBazi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class BaseController {
    @GetMapping("health-check/")
    public ResponseEntity HealthCheck() {
        return new ResponseEntity<>("Hi, this is health check api, your app is running, Thanks!", HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity DefaultAPI() {
        return new ResponseEntity<>("Hi, this is the default route", HttpStatus.OK);
    }
}
