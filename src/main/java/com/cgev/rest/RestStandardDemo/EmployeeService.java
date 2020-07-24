package com.cgev.rest.RestStandardDemo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository repository;
    private ObjectMapper mapper = new ObjectMapper();

    EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    List<EmployeeDTO> getEmployees() {
        return repository.findAll().stream()
                .map(entity ->  mapper.convertValue(entity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    EmployeeDTO getEmployee(Integer id) {
        EmployeeEntity entity = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return mapper.convertValue(entity, EmployeeDTO.class);
    }

    void deleteEmployee(Integer id) {
        repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        repository.deleteById(id);
    }

    /**
     * Adding a new employee
     * @param employee which we are going to add
     * @return new added object
     */
    EmployeeDTO addEmployee(EmployeeDTO employee) {

        return mapper.convertValue(repository.save(mapper.convertValue(employee, EmployeeEntity.class)), EmployeeDTO.class);
    }
}
