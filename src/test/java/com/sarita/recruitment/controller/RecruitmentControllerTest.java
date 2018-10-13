package com.sarita.recruitment.controller;

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


}
