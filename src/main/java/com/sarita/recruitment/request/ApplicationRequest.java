package com.sarita.recruitment.request;

import com.sarita.recruitment.model.ApplicationStatus;

public class ApplicationRequest {
    private String jobTitle;
    private Integer jobId;
    private String email;
    private String resume;
    private ApplicationStatus applicationStatus;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobTitle) {
        this.jobId = jobTitle;
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
}
