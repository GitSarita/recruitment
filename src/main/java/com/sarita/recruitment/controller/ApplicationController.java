package com.sarita.recruitment.controller;

import com.sarita.recruitment.model.Application;
import com.sarita.recruitment.model.ApplicationStatus;
import com.sarita.recruitment.request.ApplicationRequest;
import com.sarita.recruitment.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicationController {
    ApplicationService applicationService;


    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("admin/applications")
    public ResponseEntity<List> getApplications() {
        return new ResponseEntity<>(applicationService.getApplications(), HttpStatus.OK);
    }

    @GetMapping("admin/{id}/application")
    public ResponseEntity<Application> getApplications(@PathVariable Integer id) {
        return new ResponseEntity<>(applicationService.getApplication(id), HttpStatus.OK);
    }

    @GetMapping("admin/applicationsByJob")
    public ResponseEntity<List> getApplicationPerOffer(@RequestParam Integer offerId) {

        return new ResponseEntity<>(applicationService.getApplicationsPerOffer(offerId), HttpStatus.OK);
    }

    @PostMapping("/application")
    public ResponseEntity<Application> submitApplication(@RequestBody ApplicationRequest applicationRequest) {

        Application submitedapplication = applicationService.submitApplication(applicationRequest);
        return new ResponseEntity<>(submitedapplication, HttpStatus.CREATED);

    }

    @PutMapping("/admin/application/{id}/status")
    public ResponseEntity<Application> updateApplicationStatus(@RequestParam ApplicationStatus appStatus, @PathVariable Integer id) {

        Application submitedapplication = applicationService.updateStatus(appStatus, id);
        return new ResponseEntity<>(submitedapplication, HttpStatus.OK);

    }
}
