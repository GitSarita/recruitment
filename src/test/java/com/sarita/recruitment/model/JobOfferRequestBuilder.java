package com.sarita.recruitment.model;

import com.sarita.recruitment.request.JobOfferRequest;

import java.util.Date;

public class JobOfferRequestBuilder {

    private String jobTitle;
    private Date startDate;

    public JobOfferRequestBuilder() {

    }

    public JobOfferRequestBuilder(String jobTitle, Date startDate) {
        this.jobTitle = jobTitle;
        this.startDate = startDate;
    }

    public JobOfferRequestBuilder withJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public JobOfferRequestBuilder withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }
    public JobOfferRequest build(){
        return new JobOfferRequest(this.jobTitle,this.startDate);
    }
}
