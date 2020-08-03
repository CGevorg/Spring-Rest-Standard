package com.cgev.rest.RestStandardDemo.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Integer id) {
        super("Could not found resource with id : " + id);
    }
}
