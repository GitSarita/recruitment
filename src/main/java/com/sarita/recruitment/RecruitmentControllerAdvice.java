package com.sarita.recruitment;

import com.sarita.recruitment.exception.ApplicationAlreadyExists;
import com.sarita.recruitment.exception.DuplicateJobCreationException;
import com.sarita.recruitment.exception.JobOfferNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class RecruitmentControllerAdvice {

    @ExceptionHandler(JobOfferNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleJobOfferNotFoundException(final JobOfferNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApplicationAlreadyExists.class)
    @ResponseBody
    public ResponseEntity<String> handleApplicationAlreadyExists(final ApplicationAlreadyExists e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateJobCreationException.class)
    @ResponseBody
    public ResponseEntity<String> handleDuplicateJobCreationException(final DuplicateJobCreationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
