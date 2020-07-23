package com.cgev.rest.RestStandardDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResourceController {

    private final ResourceService service;

    ResourceController(ResourceService service) {
        this.service = service;
    }

    @GetMapping("/resources")
    List<Resource> getResources() {
        return service.getResources();
    }
}
