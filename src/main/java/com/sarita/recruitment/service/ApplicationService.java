package com.sarita.recruitment.service;

import com.sarita.recruitment.exception.ApplicationAlreadyExists;
import com.sarita.recruitment.exception.ApplicationNotFound;
import com.sarita.recruitment.exception.JobOfferNotFoundException;
import com.sarita.recruitment.model.Application;
import com.sarita.recruitment.model.ApplicationStatus;
import com.sarita.recruitment.ApplicationRepository;
import com.sarita.recruitment.model.JobOffer;
import com.sarita.recruitment.request.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ApplicationService {

    private ApplicationRepository applicationRepository;
    private RecruitmentService recruitmentService;

    private Function<ApplicationRequest, Application> applicationMapper
            = (ApplicationRequest applicationRequest) -> {
        Application application = new Application();
        application.setJobId(applicationRequest.getJobId());
        application.setApplicationStatus(ApplicationStatus.APPLIED);
        application.setEmail(applicationRequest.getEmail());
        application.setResume(applicationRequest.getResume());
        return application;
    };

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, RecruitmentService recruitmentService) {
        this.applicationRepository = applicationRepository;
        this.recruitmentService = recruitmentService;
    }

    @Transactional
    public Application submitApplication(ApplicationRequest applicationRequest) {
        Integer count = applicationRepository.findByEmailAndJobTitle(applicationRequest.getEmail(), applicationRequest.getJobId());
        if (count > 0) {
            throw new ApplicationAlreadyExists("Application already submitted by you");
        } else {
            return applicationRepository.save(applicationMapper.apply(applicationRequest));
        }


    }
    public Application getApplication(Integer id){
        Optional<Application> application= applicationRepository.findById(id);
        if(application.isPresent()){
            return application.get();
        }else{
            throw new ApplicationNotFound("Application Id not found");
        }
    }

    public Application updateStatus(ApplicationStatus appStatus, Integer applicationId) {
        Optional<Application> application = applicationRepository.findById(applicationId);
        if (application.isPresent()) {
            application.get().setApplicationStatus(appStatus);
            return applicationRepository.save(application.get());
        } else throw new JobOfferNotFoundException("Job not present, Application cannot be submitted");

    }

    public List<Application> getApplications() {
        return applicationRepository.findAll();
    }

    public List<Application> getApplicationsPerOffer(Integer jobId) {
        JobOffer jobOffer = recruitmentService.getJobOfferById(jobId);
        return (List<Application>) jobOffer.getApplications();
    }

}
