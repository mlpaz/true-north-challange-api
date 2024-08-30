package com.truenorth.challenge.api.exceptions;

public class OperationNotAcceptableException extends RuntimeException{
    public OperationNotAcceptableException(final String message) {
        super(message);
    }
}
