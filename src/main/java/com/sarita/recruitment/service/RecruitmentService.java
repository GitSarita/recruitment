package com.sarita.recruitment.service;

import com.sarita.recruitment.ApplicationRepository;
import com.sarita.recruitment.RecruitmentRepository;
import com.sarita.recruitment.exception.DuplicateJobCreationException;
import com.sarita.recruitment.exception.JobOfferNotFoundException;
import com.sarita.recruitment.model.JobOffer;
import com.sarita.recruitment.request.JobOfferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class RecruitmentService {
    private RecruitmentRepository recruitmentRepository;
    private ApplicationRepository applicationRepository;
    private Consumer<Optional<JobOffer>> jobOfferNotFound = (Optional<JobOffer> offer) -> {
        if (!offer.isPresent()) {
            throw new JobOfferNotFoundException("Job offer Not Found");
        }
    };

    @Autowired
    public RecruitmentService(RecruitmentRepository recruitmentRepository, ApplicationRepository applicationRepository) {
        this.recruitmentRepository = recruitmentRepository;
        this.applicationRepository = applicationRepository;
    }

    private Function<JobOfferRequest, JobOffer> getJobOffer
            = (JobOfferRequest jobOfferRequest) -> {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setJobTitle(jobOfferRequest.getJobTitle());
        jobOffer.setStartDate(jobOfferRequest.getStartDate());
        return jobOffer;
    };

    public Optional<JobOffer> createJobOffer(JobOfferRequest jobOfferRequest) {
        if (!recruitmentRepository.findByJobTitle(jobOfferRequest.getJobTitle()).isPresent()) {
            JobOffer jobOffer = recruitmentRepository.save(getJobOffer.apply(jobOfferRequest));
            return Optional.of(jobOffer);
        } else {
            throw new DuplicateJobCreationException("Job already exists with this Job Title");
        }


    }

    public Optional<JobOffer> saveJobOffer(JobOffer jobOffer) {
        JobOffer jobOfferSaved = recruitmentRepository.save(jobOffer);
        return Optional.of(jobOfferSaved);
    }

    public JobOffer getJobOfferById(Integer id) {
        Optional<JobOffer> jobOffer = recruitmentRepository.findById(id);

        jobOfferNotFound.accept(jobOffer);
        JobOffer job = jobOffer.get();
        job.setNoOfApplications(applicationRepository.countApplicationByJobTitle(job.getJobTitle()));
        return job;
    }

    public JobOffer getJobOfferByJobTitle(String jobTitle) {
        Optional<JobOffer> jobOffer = recruitmentRepository.findByJobTitle(jobTitle);
        jobOfferNotFound.accept(jobOffer);
        JobOffer job = jobOffer.get();
        job.setNoOfApplications(applicationRepository.countApplicationByJobTitle(job.getJobTitle()));
        return job;
    }

    public List<JobOffer> getJobOffers() {
        return recruitmentRepository.findAll();
    }
}
