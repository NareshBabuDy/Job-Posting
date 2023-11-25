package com.restapi.repository;

import com.restapi.model.AppliedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppliedJobRepository extends JpaRepository<AppliedJob, Long> {
    @Query("SELECT j FROM AppliedJob j WHERE j.Jobid = ?1")
    AppliedJob findByJobId(Long id);


//    @Query("SELECT a FROM Applied a JOIN a.jobs j WHERE j.company.id = ?1")
//    AppliedJob findByJobId(Long id);


}
