package com.restapi.repository;

import com.restapi.model.Jobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long> {
    @Query("SELECT j FROM Jobs j WHERE j.company.id =?1")
    List<Jobs> findByCompanyId(Long companyId);

    @Query("UPDATE Jobs j SET j.count = j.count - 1 WHERE j.id = ?1")
    Jobs decreaseJobCount(Long jobid);
}
