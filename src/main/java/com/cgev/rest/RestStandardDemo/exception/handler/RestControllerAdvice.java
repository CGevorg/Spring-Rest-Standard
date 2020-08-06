package com.cgev.rest.RestStandardDemo.exception.handler;

import com.cgev.rest.RestStandardDemo.exception.EmployeeNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestControllerAdvice {

    private MessageSource messageSource;

    RestControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    String onResourceNotFoundHandler(EmployeeNotFoundException ex) {
        //@TODO
        //CREATE ERROR_KEY class with ErrorResponse that we could localize user request based on the header
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ValidationErrorResponse errorResponse = new ValidationErrorResponse();
        List<Violation> violations = e.getBindingResult().getFieldErrors()
                .stream()
                .map(error ->
                        new Violation(error.getObjectName(), error.getField(), error.getRejectedValue(),
                                messageSource.getMessage(error.getDefaultMessage(), null, error.getDefaultMessage(), LocaleContextHolder.getLocale())))
                .collect(Collectors.toList());
        errorResponse.setViolations(violations);
        return errorResponse;
    }
}
