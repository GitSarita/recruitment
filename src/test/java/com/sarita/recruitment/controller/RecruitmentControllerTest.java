package com.sarita.recruitment.controller;

import com.sarita.recruitment.model.JobOfferRequestBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RecruitmentController.class, secure = false)
public class RecruitmentControllerTest {
    @Autowired
    private RecruitmentController recruitmentController;

    @Test
    public void testCreateJobOffer() {
        ResponseEntity<String> responseEntity = recruitmentController.createJobOffer(new JobOfferRequestBuilder()
                .withJobTitle("Java Developer")
                .withStartDate(new Date())
                .build());

        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.CREATED));

    }

}
