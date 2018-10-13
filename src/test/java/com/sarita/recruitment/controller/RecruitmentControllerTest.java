package com.sarita.recruitment.controller;

import com.sarita.recruitment.exception.JobOfferNotFoundException;
import com.sarita.recruitment.model.JobOffer;
import com.sarita.recruitment.model.JobOfferRequestBuilder;
import com.sarita.recruitment.request.JobOfferRequest;
import com.sarita.recruitment.RecruitmentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RecruitmentController.class, secure = false)
public class RecruitmentControllerTest {
    @Autowired
    private RecruitmentController recruitmentController;

    @MockBean
    private RecruitmentService recruitmentService;

    @Test
    public void testCreateJobOffer() {
        JobOfferRequest jobOfferRequest = new JobOfferRequestBuilder()
                .withJobTitle("Java Developer")
                .withStartDate(new Date())
                .build();
        Mockito.when(recruitmentService.createJobOffer(jobOfferRequest)).thenReturn(java.util.Optional.of("Java Developer"));
        ResponseEntity<String> responseEntity = recruitmentController.createJobOffer(jobOfferRequest);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        Assert.assertTrue(responseEntity.getBody().contains("Java Developer"));

    }

    @Test
    public void testCreateJobOfferNegative() {
        JobOfferRequest jobOfferRequest = new JobOfferRequestBuilder()
                .withJobTitle("Java Developer")
                .withStartDate(new Date())
                .build();
        Mockito.when(recruitmentService.createJobOffer(jobOfferRequest)).thenReturn(Optional.empty());
        ResponseEntity<String> responseEntity = recruitmentController.createJobOffer(jobOfferRequest);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertTrue(responseEntity.getBody().contains("Not Created"));

    }

    @Test(expected = JobOfferNotFoundException.class)
    public  void  testGetJobOfferNegative(){
        Mockito.when(recruitmentService.getJobOfferById(1)).thenThrow(new JobOfferNotFoundException("Not Found"));
        ResponseEntity<JobOffer> responseEntity = recruitmentController.getJobOffer(1);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
    @Test
    public  void  testGetJobOfferPositive(){
        Mockito.when(recruitmentService.getJobOfferById(1)).thenReturn(Optional.of(new JobOffer()));
        ResponseEntity<JobOffer> responseEntity = recruitmentController.getJobOffer(1);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }


}
