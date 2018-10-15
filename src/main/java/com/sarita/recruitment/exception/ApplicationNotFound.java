package com.sarita.recruitment.exception;

public class ApplicationNotFound extends RuntimeException{
    String message;

    public ApplicationNotFound(String message) {
        super(message);
    }
}
