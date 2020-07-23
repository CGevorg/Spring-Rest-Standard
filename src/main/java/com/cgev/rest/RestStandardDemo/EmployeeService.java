package com.cgev.rest.RestStandardDemo;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository repository;

    EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    List<Employee> getEmployees() {
        return repository.findAll();
    }

    Employee getEmployee(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    void deleteEmployee(Integer id) {
        repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        repository.deleteById(id);
    }
}
