package com.cgev.rest.RestStandardDemo.exception;

import com.cgev.rest.RestStandardDemo.exception.EmployeeNotFoundException;
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
        //@TODO
        //CREATE ERROR_KEY class with ErrorResponse that we could localize user request based on the header
        return ex.getMessage();
    }
}
