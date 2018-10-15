package com.sarita.recruitment.model;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer jobId;
    @Column(unique=true)
    private String jobTitle;
    private Date startDate;
    private Integer noOfApplications;

    @OneToMany(

            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY

    )
    @JoinTable(name = "JOB_APPLICATION",
            joinColumns = @JoinColumn(name = "JOB_ID"),
            inverseJoinColumns = @JoinColumn(name = "APPLICATION_ID"))
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

