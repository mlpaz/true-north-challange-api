package com.truenorth.challenge.api.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(final String message) {
        super(message);
    }
}
