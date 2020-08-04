package com.cgev.rest.RestStandardDemo.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Integer id) {
        super("Could not found resource with id : " + id);
    }
}
