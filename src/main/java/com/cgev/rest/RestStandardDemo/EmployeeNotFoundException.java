package com.cgev.rest.RestStandardDemo;

class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(Integer id) {
        super("Could not found resource with id : " + id);
    }
}
