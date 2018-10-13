package com.sarita.recruitment.model;

import javax.persistence.*;

@Entity
public class Application {
    @Id
    private Integer applicationId;
    @ManyToOne
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
}
