package com.sarita.recruitment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer applicationId;

    @ManyToOne
    @JsonIgnore
    private JobOffer offer;
    private String email;
    private String resume;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    public JobOffer getOffer() {
        return offer;
    }

    public void setOffer(JobOffer offer) {
        this.offer = offer;
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

    public Application(JobOffer offer, String email, String resume, ApplicationStatus applicationStatus) {
        this.offer = offer;
        this.email = email;
        this.resume = resume;
        this.applicationStatus = applicationStatus;
    }

    public Application() {
    }
}
