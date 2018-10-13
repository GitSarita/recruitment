package com.sarita.recruitment;

import com.sarita.recruitment.exception.JobOfferNotFoundException;
import com.sarita.recruitment.model.JobOffer;
import com.sarita.recruitment.request.JobOfferRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class RecruitmentService {
    RecruitmentRepository recruitmentRepository;

    public RecruitmentService(RecruitmentRepository recruitmentRepository) {
        this.recruitmentRepository = recruitmentRepository;
    }

    Function<JobOfferRequest, JobOffer> getJobOffer
            = (JobOfferRequest jobOfferRequest) -> {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setJobTitle(jobOfferRequest.getJobTitle());
        jobOffer.setStartDate(jobOfferRequest.getStartDate());
        return jobOffer;
    };

    public Optional<String> createJobOffer(JobOfferRequest jobOfferRequest) {
        JobOffer jobOffer = recruitmentRepository.save(getJobOffer.apply(jobOfferRequest));
        return Optional.of(jobOffer.getJobTitle());
    }

    public Optional<JobOffer> getJobOfferById(Integer id){
        Optional<JobOffer> jobOffer= recruitmentRepository.findById(id);
        if (!jobOffer.isPresent()){
            throw new JobOfferNotFoundException("Job offer Not Found");
        }
        return jobOffer;
    }
}
