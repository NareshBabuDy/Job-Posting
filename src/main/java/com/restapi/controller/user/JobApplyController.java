package com.restapi.controller.user;


import com.restapi.model.Applied;
import com.restapi.model.Role;
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
@RequestMapping("/api/job/apply")
//@PreAuthorize("hasRole('ROLE_USER')")
@RolesAllowed(Role.USER)
public class JobApplyController {


    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ApplyService applyService;

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserApplied(@PathVariable Long userId) {
        List<AppliedResponse> appliedList = applyService.findByUserId(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(appliedList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<APIResponse> UpdateJobStatus(@RequestBody AppliedRequest appliedRequest) {
        AppliedResponse applied = applyService.applyToJob(appliedRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(applied);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
