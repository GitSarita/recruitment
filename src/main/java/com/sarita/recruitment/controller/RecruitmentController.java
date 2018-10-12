package com.sarita.recruitment.controller;

import com.sarita.recruitment.model.JobOffer;
import com.sarita.recruitment.request.JobOfferRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecruitmentController {
    @PostMapping("/joboffer")
    public ResponseEntity<String> createJobOffer(@RequestBody JobOfferRequest jobOfferRequest){
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }
}
