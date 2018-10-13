package com.sarita.recruitment;

import com.sarita.recruitment.exception.JobOfferNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecruitmentControllerAdvice {

    @ExceptionHandler(JobOfferNotFoundException.class)
    public ResponseEntity<String> handleJobOfferNotFoundException(final JobOfferNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
