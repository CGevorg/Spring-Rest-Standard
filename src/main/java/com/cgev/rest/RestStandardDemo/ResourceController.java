package com.cgev.rest.RestStandardDemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @RequestMapping("/resource")
    public String getResource() {
        return "Hello world!!!";
    }
}
