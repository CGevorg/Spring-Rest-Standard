package com.cgev.rest.RestStandardDemo;

import com.cgev.rest.RestStandardDemo.exceptions.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestControllerAdvice {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    String resourceNotFoundHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }
}
