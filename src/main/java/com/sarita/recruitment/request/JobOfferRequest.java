package com.sarita.recruitment.request;

import java.util.Date;

public class JobOfferRequest {

    private String jobTitle;
    private Date startDate;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public JobOfferRequest(String jobTitle, Date startDate) {
        this.jobTitle = jobTitle;
        this.startDate = startDate;
    }
}
