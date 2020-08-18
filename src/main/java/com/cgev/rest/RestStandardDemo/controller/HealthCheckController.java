package com.cgev.rest.RestStandardDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/v1/healthcheck")
public class HealthCheckController {
    @GetMapping("/ping")
    public void ping() {
    }
}
