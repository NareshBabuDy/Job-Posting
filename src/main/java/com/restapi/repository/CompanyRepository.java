package com.restapi.repository;

import com.restapi.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    @Query("select a from Company a where a.appUser.id = ?1")
    Optional<Company> findByCompanyId(Long id);
}
