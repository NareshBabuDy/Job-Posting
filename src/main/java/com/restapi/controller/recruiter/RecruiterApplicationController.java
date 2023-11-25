package com.restapi.controller.recruiter;

import com.restapi.model.Applied;
import com.restapi.model.Role;
import com.restapi.repository.JobsRepository;
import com.restapi.request.AppliedRequest;
import com.restapi.response.AppliedResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/recruiter/application")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed({Role.RECRUITER})
public class RecruiterApplicationController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ApplyService applyService;
    @Autowired
    private JobsRepository jobsRepository;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getAppliedList(@PathVariable Long id) {
        List<AppliedResponse> appliedList = applyService.findByCompanyId(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(appliedList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateAppliedStatus(@RequestBody AppliedRequest appliedRequest) {
        AppliedResponse applied = applyService.updateJob(appliedRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(applied);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
