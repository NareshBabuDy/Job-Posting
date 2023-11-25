package com.restapi.repository;

import com.restapi.model.Applied;
import com.restapi.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppliedRepository extends JpaRepository<Applied, Long> {


    @Query("SELECT a FROM Applied a WHERE a.profile.appUser.id = ?1")
    Optional<List<Applied>> findAppliedList(Long userId);


//    @Query("select a from Applied a inner join a.appUser aUser where aUser.id = ?1")
//    Optional<List<Applied>> findAppliedList(Long userId);

    @Query("SELECT a FROM Applied a JOIN a.jobs j WHERE j.company.id = ?1")
    Optional<List<Applied>> findCompanyApplicationList(Long companyId);

}
