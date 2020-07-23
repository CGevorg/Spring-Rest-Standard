package com.cgev.rest.RestStandardDemo;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    List<Employee> getEmployees() {
        return service.getEmployees();
    }

    @GetMapping("/employees/{id}")
    Employee getEmployee(@PathVariable Integer id) {
        return service.getEmployee(id);
    }

    /**
     * Retrieve links for all employees
     * @return List of links
     */
    @GetMapping("/employees/links")
    List<EntityModel<Employee>> getEmployeeLinks() {
        return service.getEmployees().stream().map(employee ->
            EntityModel.of(employee,
                    linkTo(methodOn(EmployeeController.class).getEmployee(employee.getId())).withSelfRel())).
                collect(Collectors.toList());
    }

    @DeleteMapping("/employees/{id}")
    void deleteResource(@PathVariable Integer id) {
        service.deleteEmployee(id);
    }

    /**
     * Demo function which demonstraits that could could give them back API with which they could poll you back
     * for status retrieval or something else
     * @param id Employee Id
     * @return Response with links to another API's
     */
    @PostMapping("/employees/{id}/startworking")
    EntityModel<String> startWorking(@PathVariable Integer id) {
        return EntityModel.of("Some information that it is startet doing some job (instead of string could be any object)",
                linkTo(methodOn(EmployeeController.class).getEmployee(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).getEmployees()).withRel("employees"));
    }
}