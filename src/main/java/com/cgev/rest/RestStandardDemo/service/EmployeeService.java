package com.cgev.rest.RestStandardDemo.service;


import com.cgev.rest.RestStandardDemo.dto.EmployeeDTO;
import com.cgev.rest.RestStandardDemo.exception.EmployeeNotFoundException;
import com.cgev.rest.RestStandardDemo.model.EmployeeEntity;
import com.cgev.rest.RestStandardDemo.repository.EmployeeRepository;
import com.cgev.rest.RestStandardDemo.util.mapper.CustomObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final CustomObjectMapper mapper;

    EmployeeService(EmployeeRepository repository, CustomObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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

    /**
     * Deleting Employee
     * @param id
     */
    public void deleteEmployee(Integer id) {
        deleteEmployees(Arrays.asList(id));
    }

    /**
     * Removing Employees
     * @param ids
     */
    public void deleteEmployees(List<Integer> ids) {
        for(Integer id : ids) {
            repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
            repository.deleteById(id);
        }
    }

    /**
     * Adding a new employee
     * @param employee which we are going to add
     * @return new added object
     */
    public EmployeeDTO addEmployee(EmployeeDTO employee) {

        return mapper.convertValue(repository.save(mapper.convertValue(employee, EmployeeEntity.class)), EmployeeDTO.class);
    }

    /**
     * Update employee or add a new one
     * @param employee which we are going to update or add
     * @return updated or created employee
     */
    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
        return mapper.convertValue(repository.save(mapper.convertValue(employee, EmployeeEntity.class)), EmployeeDTO.class);
    }
}
