package com.sarita.recruitment.exception;

public class DuplicateJobCreationException extends RuntimeException {
    String message;

    public DuplicateJobCreationException(String message) {
        super(message);
        this.message = message;
    }
}
