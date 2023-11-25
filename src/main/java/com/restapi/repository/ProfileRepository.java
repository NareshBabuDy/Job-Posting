package com.restapi.repository;

import com.restapi.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("select a from Profile a where a.appUser.id = ?1")
    Optional<Profile> findByUserId(Long userId);

}
