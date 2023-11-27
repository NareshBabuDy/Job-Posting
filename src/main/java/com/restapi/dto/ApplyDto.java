package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.model.Applied;
import com.restapi.model.Profile;
import com.restapi.repository.UserRepository;
import com.restapi.request.AppliedRequest;
import com.restapi.response.AppliedResponse;
import com.restapi.service.JobService;
import com.restapi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplyDto {
    @Autowired
    private JobService jobService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserRepository userRepository;

    public Applied mapToApplied(AppliedRequest appliedRequest) {
        Applied applied = new Applied();
        if (appliedRequest.getId() != null) {
            applied.setId(appliedRequest.getId());
        }
            applied.setStatus(appliedRequest.getStatus());
        return applied;
    }

    public AppliedResponse mapToAppliedResponse(Applied applied) {
        AppliedResponse appliedResponse = new AppliedResponse();
        appliedResponse.setId(applied.getId());
        appliedResponse.setProfileId(applied.getProfile().getId());
        appliedResponse.setProfileName(applied.getProfile().getFirstName()+applied.getProfile().getLastName());
        appliedResponse.setJobId(applied.getJobs().getId());
        appliedResponse.setJobName(applied.getJobs().getTitle());
        appliedResponse.setStatus(applied.getStatus());
        appliedResponse.setDateOfApply(String.valueOf(applied.getAppliedDate()));
        return appliedResponse;
    }
}
