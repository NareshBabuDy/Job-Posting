package com.restapi.controller.recruiter;

import com.restapi.model.Jobs;
import com.restapi.model.Profile;
import com.restapi.model.Role;
import com.restapi.request.JobRequest;
import com.restapi.response.JobResponse;
import com.restapi.response.ProfileResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.JobService;
import com.restapi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/recruiter/jobs")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed({Role.ADMIN, Role.RECRUITER})


public class RecruiterJobController {

    @Autowired
    private APIResponse apiResponse;


    @Autowired
    private JobService jobService;
    @Autowired
    private ProfileService profileService;

    @GetMapping("/all/{id}")
    public ResponseEntity<APIResponse> getJobs(@PathVariable Long id) {
        List<Jobs> jobsList = jobService.findByCompanyId(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(jobsList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> createJob(@RequestBody
                                                 JobRequest jobRequest) {
        List<JobResponse> bookList = jobService.createJob(jobRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(bookList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateJob(@RequestBody
                                                 JobRequest jobRequest) {
        List<JobResponse> jobsList = jobService.updateJob(jobRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(jobsList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteJobs(@PathVariable Integer id) {
        List<JobResponse> jobsList = jobService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(jobsList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<APIResponse> getUserProfile(@PathVariable Long id) {
        ProfileResponse profile = profileService.findById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(profile);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
