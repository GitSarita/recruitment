package com.sarita.recruitment.controller;

import com.sarita.recruitment.model.JobOffer;
import com.sarita.recruitment.request.JobOfferRequest;
import com.sarita.recruitment.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RecruitmentController {
    private RecruitmentService recruitmentService;

    @Autowired
    public RecruitmentController(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    public RecruitmentController() {

    }

    @PostMapping("/joboffer")
    public ResponseEntity<JobOffer> createJobOffer(@RequestBody JobOfferRequest jobOfferRequest) {
        Optional<JobOffer> jobOffer = recruitmentService.createJobOffer(jobOfferRequest);
        return new ResponseEntity<>(jobOffer.get() , HttpStatus.CREATED);
    }

    @GetMapping("/{id}/joboffer")
    public ResponseEntity<JobOffer> getJobOffer(@PathVariable Integer id) {
        JobOffer jobOffer = recruitmentService.getJobOfferById(id);
        return new ResponseEntity<>(jobOffer, HttpStatus.OK);

    }

    @GetMapping("/joboffer")
    public ResponseEntity<List> getJobAllOffer() {
        List<JobOffer> jobOffers = recruitmentService.getJobOffers();
        return new ResponseEntity<>(jobOffers, HttpStatus.OK);

    }




}
