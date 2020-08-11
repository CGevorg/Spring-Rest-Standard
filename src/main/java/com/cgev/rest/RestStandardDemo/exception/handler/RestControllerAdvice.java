package com.cgev.rest.RestStandardDemo.exception.handler;

import com.cgev.rest.RestStandardDemo.exception.EmployeeNotFoundException;
import com.cgev.rest.RestStandardDemo.util.MessageKey;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
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
        errorResponse.setMessage(getLocalizedMessage(MessageKey.VALIDATION_FAILED));
        List<Violation> violations = e.getBindingResult().getFieldErrors()
                .stream()
                .map(error ->
                        new Violation(error.getObjectName(), error.getField(), error.getRejectedValue(),
                                getLocalizedMessage(error.getDefaultMessage())))
                .collect(Collectors.toList());
        errorResponse.setViolations(violations);
        return errorResponse;
    }

    /**
     * Handle violation exceptions.
     * In most cases used when controller tagged with @Validated annotation and inside Validating list of DTOs
     *
     * @param e exception
     * @return wrapped result
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintViolationException(ConstraintViolationException e) {
        ValidationErrorResponse errorResponse = new ValidationErrorResponse();
        errorResponse.setMessage(getLocalizedMessage(MessageKey.VALIDATION_FAILED));
        List<Violation> violations = e.getConstraintViolations()
                .stream()
                .map(violation -> new Violation(violation.getPropertyPath().toString(),
                        violation.getPropertyPath().toString(),
                        violation.getInvalidValue(),
                        getLocalizedMessage(violation.getMessage())))
                .collect(Collectors.toList());
        errorResponse.setViolations(violations);
        return errorResponse;
    }

    private String getLocalizedMessage(String key) {
        return messageSource.getMessage(key, null, key, LocaleContextHolder.getLocale());
    }
}
