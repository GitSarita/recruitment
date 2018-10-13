package com.sarita.recruitment;

import com.sarita.recruitment.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RecruitmentRepository extends JpaRepository<JobOffer,String> {


}
