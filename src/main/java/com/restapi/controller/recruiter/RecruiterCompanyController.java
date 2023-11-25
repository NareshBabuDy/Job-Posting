package com.restapi.controller.recruiter;


import com.restapi.model.Company;
import com.restapi.model.Role;
import com.restapi.request.CompanyRequest;
import com.restapi.response.CompanyResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping("/api/recruiter/company")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed({Role.RECRUITER})

public class RecruiterCompanyController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getCompanyDetails(@PathVariable Long id) {
        CompanyResponse company = companyService.findByCompanyId(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(company);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> createCompanyDetails(@RequestBody CompanyRequest companyRequest) {
        CompanyResponse company = companyService.createCompanyDetails(companyRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(company);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateCompanyDetails(@RequestBody CompanyRequest companyRequest) {
        CompanyResponse company = companyService.createCompanyDetails(companyRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(company);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}