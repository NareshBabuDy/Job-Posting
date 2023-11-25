package com.restapi.service;

import com.restapi.dto.ApplyDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.AppliedJobRepository;
import com.restapi.repository.AppliedRepository;
import com.restapi.repository.ProfileRepository;
import com.restapi.request.AppliedRequest;
import com.restapi.response.AppliedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ApplyService {
    @Autowired
    private ApplyDto applyDto;
    @Autowired
    private UserService userService;
    @Autowired
    private JobService jobService;

    @Autowired
    private AppliedRepository appliedRepository;

    @Autowired
    private AppliedJobRepository appliedJobRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Transactional
    public AppliedResponse applyToJob(AppliedRequest appliedRequest) {
        Profile profile = profileRepository.findById(appliedRequest.getProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("company", "company", appliedRequest.getProfileId()));
        Jobs jobs = jobService.findById(appliedRequest.getJobId());
        AppliedJob appliedJob1 = new AppliedJob();

        if (appliedJobRepository.findByJobId(jobs.getId()) != null) {
            appliedJob1 = appliedJobRepository.findByJobId(jobs.getId());
        } else {
            appliedJob1.setJobid(jobs.getId());
            appliedJob1.setTitle(jobs.getTitle());
            appliedJob1.setDescription(jobs.getDescription());
            appliedJob1.setCount(jobs.getCount());
            appliedJob1.setLastdate(jobs.getLastdate());
            appliedJob1.setCategory(jobs.getCategory().getId());
            appliedJob1.setCategoryName(jobs.getCategory().getCategory());
            appliedJob1.setCompany(jobs.getCompany());
            appliedJobRepository.save(appliedJob1);

        }

        Optional<List<Applied>> appliedList = appliedRepository.findAppliedList(appliedRequest.getProfileId());
        if (appliedList.isPresent()) {
            for (Applied applied : appliedList.get()) {
                if (applied.getMainJobid().getId().equals(appliedRequest.getJobId())) {
                    return applyDto.mapToAppliedResponse(applied);
                }
            }
        }
        Applied newapplied = applyDto.mapToApplied(appliedRequest);
        newapplied.setProfile(profile);
        newapplied.setMainJobid(jobs);
        newapplied.setJobs(appliedJob1);
        appliedRepository.save(newapplied);
        return applyDto.mapToAppliedResponse(newapplied);
    }

    public List<AppliedResponse> findByUserId(Long userId) {
        List<Applied> appliedList = appliedRepository.findAppliedList(userId)
                .orElseThrow(() -> new ResourceNotFoundException("applied", "userId", userId));
        List<AppliedResponse> appliedResponseList = new ArrayList<>();
        for (Applied applied : appliedList) {
            AppliedResponse appliedResponse = applyDto.mapToAppliedResponse(applied);
            appliedResponseList.add(appliedResponse);
        }
        return appliedResponseList;
    }

    public List<AppliedResponse> findByCompanyId(Long id) {
        List<Applied> appliedList = appliedRepository.findCompanyApplicationList(id)
                .orElseThrow(() -> new ResourceNotFoundException("company", "company", id));
        List<AppliedResponse> appliedResponseList = new ArrayList<>();
        for (Applied applied : appliedList) {
            AppliedResponse appliedResponse = applyDto.mapToAppliedResponse(applied);
            appliedResponseList.add(appliedResponse);
        }
        return appliedResponseList;
    }


    public AppliedResponse updateJob(AppliedRequest appliedRequest) {
        Applied applied = applyDto.mapToApplied(appliedRequest);
        applied.setId(appliedRequest.getId());
        Profile profile = profileRepository.findById(appliedRequest.getProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("company", "company", appliedRequest.getProfileId()));
        Jobs jobs = jobService.findById(appliedRequest.getJobId());
        AppliedJob appliedJob1 = new AppliedJob();

        if (appliedJobRepository.findByJobId(jobs.getId()) != null) {
            appliedJob1 = appliedJobRepository.findByJobId(jobs.getId());
        } else {
            appliedJob1.setJobid(jobs.getId());
            appliedJob1.setTitle(jobs.getTitle());
            appliedJob1.setDescription(jobs.getDescription());
            appliedJob1.setCount(jobs.getCount());
            appliedJob1.setLastdate(jobs.getLastdate());
            appliedJob1.setCategory(jobs.getCategory().getId());
            appliedJob1.setCategoryName(jobs.getCategory().getCategory());
            appliedJob1.setCompany(jobs.getCompany());
            appliedJobRepository.save(appliedJob1);

        }
        applied.setProfile(profile);
        applied.setMainJobid(jobs);
        applied.setJobs(appliedJob1);
        appliedRepository.save(applied);
        return applyDto.mapToAppliedResponse(applied);
    }
}
