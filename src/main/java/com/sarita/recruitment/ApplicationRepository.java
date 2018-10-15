package com.sarita.recruitment;

import com.sarita.recruitment.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    @Query("SELECT COUNT(a) FROM Application a WHERE a.offer.jobTitle=?1")
    Integer countApplicationByJobTitle(String jobTitle);

    @Query("SELECT count(a) FROM Application a WHERE a.email=?1 and a.offer.jobId=?2")
    Integer findByEmailAndJobTitle(String email, Integer jobId);
}
