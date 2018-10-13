package com.sarita.recruitment.controller;

import com.sarita.recruitment.model.JobOffer;
import com.sarita.recruitment.request.JobOfferRequest;
import com.sarita.recruitment.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> createJobOffer(@RequestBody JobOfferRequest jobOfferRequest) {
        Optional<String> jobTitle = recruitmentService.createJobOffer(jobOfferRequest);
        return jobTitle.map(s -> new ResponseEntity<>("Created - " + s, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>("Not Created", HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    public ResponseEntity<JobOffer> getJobOffer(@RequestParam Integer id) {
        Optional<JobOffer> jobOffer = recruitmentService.getJobOfferById(id);
        return new ResponseEntity<>(jobOffer.get(), HttpStatus.OK);

    }
}
