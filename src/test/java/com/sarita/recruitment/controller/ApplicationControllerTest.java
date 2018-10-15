package com.sarita.recruitment.controller;

import com.sarita.recruitment.model.Application;
import com.sarita.recruitment.model.ApplicationStatus;
import com.sarita.recruitment.request.ApplicationRequest;
import com.sarita.recruitment.service.ApplicationService;
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

@RunWith(SpringRunner.class)
@WebMvcTest(value = ApplicationController.class, secure = false)
public class ApplicationControllerTest {

    @Autowired
    private ApplicationController applicationController;

    @MockBean
    private ApplicationService applicationService;

    @Test
    public void testCreateJobApplication() {
        // TODO should be converted to Builder
        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setEmail("a@b.com");
        applicationRequest.setJobId(1);
        applicationRequest.setResume("my resume");

        Mockito.when(applicationService.submitApplication(applicationRequest)).thenReturn(new Application());
        ResponseEntity<Application> responseEntity = applicationController.submitApplication(applicationRequest);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void testUpdateApplicationStatus(){
        Mockito.when(applicationService.updateStatus(ApplicationStatus.HIRED,1)).thenReturn(new Application());
        ResponseEntity<Application> responseEntity = applicationController.updateApplicationStatus(ApplicationStatus.HIRED,1);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
