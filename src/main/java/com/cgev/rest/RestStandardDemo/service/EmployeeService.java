package com.cgev.rest.RestStandardDemo.service;


import com.cgev.rest.RestStandardDemo.dto.EmployeeDTO;
import com.cgev.rest.RestStandardDemo.model.EmployeeEntity;
import com.cgev.rest.RestStandardDemo.repository.EmployeeRepository;
import com.cgev.rest.RestStandardDemo.exception.EmployeeNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository repository;
    private ObjectMapper mapper = new ObjectMapper();

    EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Collection<EmployeeDTO> getEmployees() {
        return repository.findAll().stream()
                .map(entity ->  mapper.convertValue(entity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployee(Integer id) {
        EmployeeEntity entity = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return mapper.convertValue(entity, EmployeeDTO.class);
    }

    public void deleteEmployee(Integer id) {
        repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        repository.deleteById(id);
    }

    /**
     * Adding a new employee
     * @param employee which we are going to add
     * @return new added object
     */
    public EmployeeDTO addEmployee(EmployeeDTO employee) {

        return mapper.convertValue(repository.save(mapper.convertValue(employee, EmployeeEntity.class)), EmployeeDTO.class);
    }
}
