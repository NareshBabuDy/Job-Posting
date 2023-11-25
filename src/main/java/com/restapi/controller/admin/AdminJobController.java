package com.restapi.controller.admin;

import com.restapi.model.Jobs;
import com.restapi.model.Role;
import com.restapi.response.JobResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/job")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed(Role.ADMIN)

public class AdminJobController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private JobService jobService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getJobs() {
        List<JobResponse> jobsList = jobService.findAll();
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
}
