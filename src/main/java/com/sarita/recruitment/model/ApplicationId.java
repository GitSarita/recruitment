package com.sarita.recruitment.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ApplicationId implements Serializable {
    @Column(name = "JOB_TITLE")
    private String jobTitle;
    @Column(name = "EMAIL")
    private String email;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public ApplicationId(String jobTitle, String email) {
        this.jobTitle = jobTitle;
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
