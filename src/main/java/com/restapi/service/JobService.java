package com.restapi.service;

import com.restapi.dto.JobsDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Company;
import com.restapi.model.Jobs;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.CompanyRepository;
import com.restapi.repository.JobsRepository;
import com.restapi.request.JobRequest;
import com.restapi.response.JobResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobsDto jobsDto;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobsRepository jobsRepository;

    public List<JobResponse> findAll() {
        List<Jobs> jobs = jobsRepository.findAll();
        List<JobResponse> jobResponseList = new ArrayList<>();
        for (Jobs job1 : jobs) {
            JobResponse jobResponse = jobsDto.mapToJobResponse(job1);
            jobResponseList.add(jobResponse);
        }
        return jobResponseList;
    }

    @Transactional
    public List<JobResponse> createJob(JobRequest jobRequest) {
        Jobs jobs = JobsDto.mapToJobs(jobRequest);
        Category category = categoryRepository.findById(jobRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", jobRequest.getCategoryId()));
        jobs.setCategory(category);

        Company company = companyRepository.findByCompanyId(jobRequest.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("CompanyID",
                        "CompanyID", jobRequest.getCompanyId()));
        jobs.setCompany(company);

        jobsRepository.save(jobs);
        return findAll();
    }

    public List<JobResponse> updateJob(JobRequest jobRequest) {
        Jobs jobs = JobsDto.mapToJobs(jobRequest);
        System.out.println(jobs);
        Category category = categoryService.findById(jobRequest.getCategoryId());
        jobs.setCategory(category);
        System.out.println(category);
        System.out.println("cateset");
        Company company = companyRepository.findByCompanyId(jobRequest.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("CompanyID",
                        "CompanyID", jobRequest.getCompanyId()));
        System.out.println("company");
        jobs.setCompany(company);
        System.out.println("companye");
        jobsRepository.save(jobs);
        return findAll();
    }

    public List<JobResponse> deleteById(Integer id) {
        jobsRepository.deleteById(Long.valueOf(id));
        return findAll();
    }


    public List<JobResponse> findByCompanyId(Long id) {
        List<Jobs> jobs = jobsRepository.findByCompanyId(id);
        List<JobResponse> jobResponses = new ArrayList<>();
        for (Jobs jobs1 : jobs){
            JobResponse jobResponse = jobsDto.mapToJobResponse(jobs1);
            jobResponses.add(jobResponse);
        }
        return jobResponses;
    }

    public Jobs findById(Long jobId) {
        Jobs jobs = jobsRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("JobId", "JobId", jobId));
        return jobs;
    }

    public Jobs decreaseCount(Long jobid) {
        return jobsRepository.decreaseJobCount(jobid);
    }
}
