package com.sarita.recruitment.controller;

import com.sarita.recruitment.RecruitmentApplication;
import com.sarita.recruitment.model.JobOffer;
import com.sarita.recruitment.model.JobOfferRequestBuilder;
import com.sarita.recruitment.request.JobOfferRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecruitmentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecruitmentControllerIntegrationTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void testCreateJob() throws Exception {

        JobOfferRequest jobOfferRequest = new JobOfferRequestBuilder()
                .withJobTitle("Java Developertestcreate")
                .withStartDate(new Date())
                .build();
        HttpEntity<JobOfferRequest> entity = new HttpEntity<>(jobOfferRequest, headers);

        ResponseEntity<JobOffer> response = restTemplate.exchange(
                createURLWithPort("/joboffer"),
                HttpMethod.POST, entity, JobOffer.class);

        Assert.assertTrue(response.getStatusCode()==HttpStatus.CREATED);

    }

    @Test
    public void testFindJob() throws Exception {
        JobOfferRequest jobOfferRequest = new JobOfferRequestBuilder()
                .withJobTitle("Java Developer1")
                .withStartDate(new Date())
                .build();
        HttpEntity<JobOfferRequest> entity = new HttpEntity<>(jobOfferRequest, headers);

        ResponseEntity<JobOffer> createJobResponse = restTemplate.exchange(
                createURLWithPort("/joboffer"),
                HttpMethod.POST, entity, JobOffer.class);

        Assert.assertTrue(createJobResponse.getStatusCode()==HttpStatus.CREATED);
        HttpEntity<String> getJobEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> getJobResponse = restTemplate.exchange(
                createURLWithPort("//"+createJobResponse.getBody().getJobTitle()+"//joboffer"),
                HttpMethod.GET, getJobEntity, String.class);

        Assert.assertTrue(getJobResponse.getStatusCode()==HttpStatus.OK);

    }


    }
