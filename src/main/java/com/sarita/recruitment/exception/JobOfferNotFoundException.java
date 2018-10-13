package com.sarita.recruitment.exception;

public class JobOfferNotFoundException extends RuntimeException {
    String message;

    public JobOfferNotFoundException(String message) {
        this.message = message;
    }
}
