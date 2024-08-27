package com.truenorth.challenge.api.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
