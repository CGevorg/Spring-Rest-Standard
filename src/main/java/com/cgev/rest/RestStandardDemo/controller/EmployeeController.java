package com.cgev.rest.RestStandardDemo.controller;

import com.cgev.rest.RestStandardDemo.dto.EmployeeDTO;
import com.cgev.rest.RestStandardDemo.service.EmployeeService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO employee) {
        EmployeeDTO employeeDTO = service.addEmployee(employee);
        return EntityModel.of(employeeDTO,
                linkTo(methodOn(EmployeeController.class).getEmployee(employeeDTO.getId())).withSelfRel());
    }

    @GetMapping("/employees/{id}")
    EmployeeDTO getEmployee(@PathVariable Integer id) {
        return service.getEmployee(id);
    }

    /**
     * @param employee
     * @return
     * //@TODO If the resource already exists it should return 200
     */
    @PutMapping("/employees/{id}")
    EmployeeDTO updateEmployee(@RequestBody @Valid EmployeeDTO employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Integer id) {
        service.deleteEmployee(id);
    }


    /**
     * Retrieve links for all employees
     * @return List of links
     *//*
    @GetMapping("/employees/links")
    Collection<EntityModel<EmployeeDTO>> getEmployeeLinks() {
        return service.getEmployees().stream().map(employee ->
            EntityModel.of(employee,
                    linkTo(methodOn(EmployeeController.class).getEmployee(employee.getId())).withSelfRel())).
                collect(Collectors.toList());
    }

    *//**
     * Demo function which demonstraits that could could give them back API with which they could poll you back
     * for status retrieval or something else
     * @param id Employee Id
     * @return Response with links to another API's
     *//*
    @PostMapping("/employees/{id}/startworking")
    EntityModel<String> startWorking(@PathVariable Integer id) {
        return EntityModel.of("Some information that it is startet doing some job (instead of string could be any object)",
                linkTo(methodOn(EmployeeController.class).getEmployee(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).getEmployees()).withRel("employees"));
    }

    @GetMapping("/employees")
    Collection<EmployeeDTO> getEmployees() {
        return service.getEmployees();
    }*/
}