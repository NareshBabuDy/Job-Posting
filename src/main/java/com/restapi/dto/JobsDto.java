package com.restapi.dto;

import com.restapi.model.Category;
import com.restapi.model.Jobs;
import com.restapi.repository.CategoryRepository;
import com.restapi.request.JobRequest;
import com.restapi.response.JobResponse;
import com.restapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class JobsDto {
    public JobResponse mapToJobResponse(Jobs job) {
        JobResponse jobResponse= new JobResponse();
        jobResponse.setId(job.getId());
        jobResponse.setTitle(job.getTitle());
        jobResponse.setDescription(job.getDescription());
        jobResponse.setCount(job.getCount());
        jobResponse.setLastdate(job.getLastdate());
        jobResponse.setPostedDate(String.valueOf(job.getCreatedAt()));
        jobResponse.setCategoryId(job.getCategory().getCategory());
        jobResponse.setCompanyId(job.getCompany().getId());
        return jobResponse;
    }

    public static Jobs mapToJobs(JobRequest jobRequest) {
        Jobs jobs = new Jobs();
        if (jobRequest.getId() != null) {
            jobs.setId(jobRequest.getId());
        }
        jobs.setTitle(jobRequest.getTitle());
        jobs.setDescription(jobRequest.getDescription());
        jobs.setCount(jobRequest.getCount());
        jobs.setLastdate(jobRequest.getLastdate());
        return jobs;
    }
}
