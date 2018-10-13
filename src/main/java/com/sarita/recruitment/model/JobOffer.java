package com.sarita.recruitment.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table
public class JobOffer {
    @Id
    @GeneratedValue
    private Integer jobId;
    private String jobTitle;
    private Date startDate;
    private Integer noOfApplications;
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany
    private Collection<Application> applications = new ArrayList<>();

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Collection<Application> getApplications() {
        return applications;
    }

    public void setApplications(Collection<Application> applications) {
        this.applications = applications;
    }

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

    public JobOffer(Integer jobId, String jobTitle, Date startDate, Integer noOfApplications, Collection<Application> applications) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.startDate = startDate;
        this.noOfApplications = noOfApplications;
        this.applications = applications;
    }

    public JobOffer() {
    }
}

