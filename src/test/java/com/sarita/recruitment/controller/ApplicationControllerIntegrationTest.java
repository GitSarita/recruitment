package com.sarita.recruitment.controller;

import com.sarita.recruitment.RecruitmentApplication;
import com.sarita.recruitment.model.Application;
import com.sarita.recruitment.model.ApplicationStatus;
import com.sarita.recruitment.model.JobOffer;
import com.sarita.recruitment.model.JobOfferRequestBuilder;
import com.sarita.recruitment.request.ApplicationRequest;
import com.sarita.recruitment.request.JobOfferRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecruitmentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void testSubmitApplication() throws Exception {

        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setEmail("a@b.com");

        applicationRequest.setResume("my resume");

        JobOfferRequest jobOfferRequest = new JobOfferRequestBuilder()
                .withJobTitle("Java Developeruio")
                .withStartDate(new Date())
                .build();
        HttpEntity<ApplicationRequest> entity = new HttpEntity<>(applicationRequest);

        ResponseEntity<JobOffer> offer = restTemplate.exchange(
                createURLWithPort("/joboffer"),
                HttpMethod.POST, entity, JobOffer.class);
        applicationRequest.setJobId(offer.getBody().getJobId());

        ResponseEntity<Application> response = restTemplate.exchange(
                createURLWithPort("/application"),
                HttpMethod.POST, entity, Application.class);

        Assert.assertTrue(response.getStatusCode() == HttpStatus.CREATED);

    }

    @Test
    public void testUpdateApplication() throws Exception {

        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setEmail("a@b.com");
        applicationRequest.setJobId(1);
        applicationRequest.setResume("my resume");

        JobOfferRequest jobOfferRequest = new JobOfferRequestBuilder()
                .withJobTitle("Java Developer")
                .withStartDate(new Date())
                .build();
        HttpEntity<JobOfferRequest> entity = new HttpEntity<>(jobOfferRequest);

        ResponseEntity<JobOffer> offer = restTemplate.exchange(
                createURLWithPort("/joboffer"),
                HttpMethod.POST, entity, JobOffer.class);

        Assert.assertTrue(offer.getStatusCode() == HttpStatus.CREATED);
        applicationRequest.setJobId(offer.getBody().getJobId());
        HttpEntity<ApplicationRequest> applicationRequestentity = new HttpEntity<>(applicationRequest);
        ResponseEntity<Application> applicationResponse = restTemplate.exchange(
                createURLWithPort("/application"),
                HttpMethod.POST, applicationRequestentity, Application.class);

        Assert.assertTrue(applicationResponse.getStatusCode() == HttpStatus.CREATED);

        HttpEntity<ApplicationStatus> applicationStatus = new HttpEntity<ApplicationStatus>(ApplicationStatus.HIRED);

        String url = "http://localhost:" + this.port;
        URI uri = UriComponentsBuilder.fromHttpUrl(url).path("/admin/application/" + applicationResponse.getBody().getApplicationId() + "/status")
                .queryParam("appStatus","HIRED").build().toUri();


        ResponseEntity<Application> statusResponse = restTemplate.exchange(
                uri,
                HttpMethod.PUT, applicationStatus, Application.class);

        Assert.assertTrue(statusResponse.getStatusCode() == HttpStatus.OK);
        Assert.assertEquals(statusResponse.getBody().getApplicationStatus(), ApplicationStatus.HIRED);

    }
}
