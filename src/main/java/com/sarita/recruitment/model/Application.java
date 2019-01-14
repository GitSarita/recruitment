package com.sarita.recruitment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer applicationId;

    @Column
    private Integer jobId;
    @Column
    private String email;
    @Column
    private String resume;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Application(Integer jobId, String email, String resume, ApplicationStatus applicationStatus) {
        this.jobId = jobId;
        this.email = email;
        this.resume = resume;
        this.applicationStatus = applicationStatus;
    }

    public Application() {
    }
}
