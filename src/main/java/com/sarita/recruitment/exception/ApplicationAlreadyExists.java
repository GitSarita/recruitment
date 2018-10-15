package com.sarita.recruitment.exception;

public class ApplicationAlreadyExists extends RuntimeException {

    String message;

    public ApplicationAlreadyExists(String message) {

        super(message);
        this.message = message;
    }
}
