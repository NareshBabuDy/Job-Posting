package com.restapi.controller.user;

import com.restapi.model.Jobs;
import com.restapi.response.JobResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")

public class JobController {
    @Autowired
    private APIResponse apiResponse;


    @Autowired
    private JobService jobService;

    @GetMapping()
    public ResponseEntity<APIResponse> getJobs() {
        List<JobResponse> jobsList = jobService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(jobsList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
