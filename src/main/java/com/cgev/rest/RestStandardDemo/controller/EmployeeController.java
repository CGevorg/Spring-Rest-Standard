package com.cgev.rest.RestStandardDemo.controller;

import com.cgev.rest.RestStandardDemo.dto.EmployeeDTO;
import com.cgev.rest.RestStandardDemo.service.EmployeeService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Validated
@RestController
public class EmployeeController {

    private final EmployeeService service;

    EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping({"/v1/employees",
    "/v2/employees"})
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO employee) {
        EmployeeDTO employeeDTO = service.addEmployee(employee);
        return EntityModel.of(employeeDTO,
                linkTo(methodOn(EmployeeController.class).getEmployee(employeeDTO.getId())).withSelfRel());
    }

    @GetMapping("/v1/employees/{id}")
    EmployeeDTO getEmployee(@PathVariable Integer id) {
        return service.getEmployee(id);
    }

    @GetMapping("/v1/employees")
    Collection<EmployeeDTO> getEmployees() {
        return service.getEmployees();
    }

    /**
     * @param employee
     * @return
     * //@TODO If the resource already exists it should return 200
     */
    @PutMapping("/v1/employees/{id}")
    EmployeeDTO updateEmployee(@RequestBody @Valid EmployeeDTO employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("/v1/employees/{id}")
    void deleteEmployee(@PathVariable Integer id) {
        service.deleteEmployee(id);
    }

    @DeleteMapping("/v1/employees")
    void deleteEmployee(@RequestParam List<Integer> ids) {
        service.deleteEmployees(ids);
    }
}