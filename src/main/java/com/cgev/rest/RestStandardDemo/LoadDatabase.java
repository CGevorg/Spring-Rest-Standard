package com.cgev.rest.RestStandardDemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {


    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            repository.save(new EmployeeEntity("1.1", "1.2"));
            repository.save(new EmployeeEntity("2.1", "2.2"));
        };
    }
}
