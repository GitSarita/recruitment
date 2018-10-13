package com.sarita.recruitment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarita.recruitment.RecruitmentApplication;
import com.sarita.recruitment.RecruitmentService;
import com.sarita.recruitment.model.JobOfferRequestBuilder;
import com.sarita.recruitment.request.JobOfferRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void testIndex() throws Exception {

        JobOfferRequest jobOfferRequest = new JobOfferRequestBuilder()
                .withJobTitle("Java Developer")
                .withStartDate(new Date())
                .build();
        HttpEntity<JobOfferRequest> entity = new HttpEntity<>(jobOfferRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/joboffer"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue(response.getStatusCode()==HttpStatus.CREATED);

    }
}
