package com.sarita.recruitment;

import com.sarita.recruitment.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruitmentRepository extends JpaRepository<JobOffer,Integer> {


    Optional<JobOffer> findByJobTitle(String jobTitle);
}
