package com.cgev.rest.RestStandardDemo;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private ResourceRepository repository;

    ResourceService(ResourceRepository repository) {
        this.repository = repository;
    }

    List<Resource> getResources() {
        return repository.findAll();
    }
}
