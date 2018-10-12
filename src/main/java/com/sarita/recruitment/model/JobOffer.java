package com.sarita.recruitment.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table
public class JobOffer {

    private String jobTitle;
    private Date startDate;
    private Integer noOfApplications;

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

    public Integer getNoOfApplications() {
        return noOfApplications;
    }

    public void setNoOfApplications(Integer noOfApplications) {
        this.noOfApplications = noOfApplications;
    }


}
