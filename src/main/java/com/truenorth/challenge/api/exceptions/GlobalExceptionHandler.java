package com.truenorth.challenge.api.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDetails resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorDetails(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(value = {BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails resourceBadRequest(BadRequestException ex, WebRequest request) {
        return new ErrorDetails(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(value = {OperationNotAcceptableException.class})
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorDetails operationNotAcceptable(OperationNotAcceptableException ex, WebRequest request) {
        return new ErrorDetails(ex.getMessage(), request.getDescription(false));
    }


    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError constraintViolationException(ConstraintViolationException ex, WebRequest mockWebRequest) {
        ValidationError errors = new ValidationError();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            errors.addViolations(new ErrorDetails(violation.getMessage(), violation.getPropertyPath().toString()));
        }
        return errors;
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError constraintViolationException(MethodArgumentNotValidException ex) {
        ValidationError errors = new ValidationError();
        for (FieldError violation : ex.getBindingResult().getFieldErrors()) {
            errors.addViolations(new ErrorDetails(violation.getDefaultMessage(), violation.getField()));
        }
        return errors;
    }
}
