package com.restapi.controller.admin;

import com.restapi.model.AppUser;
import com.restapi.model.Company;
import com.restapi.model.Profile;
import com.restapi.model.Role;
import com.restapi.response.AuthResponse;
import com.restapi.response.CompanyResponse;
import com.restapi.response.ProfileResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CompanyService;
import com.restapi.service.ProfileService;
import com.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/admin/")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed(Role.ADMIN)
public class AdminUserController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/user/all")
    public ResponseEntity<APIResponse> getAllUsers() {
        List<AuthResponse> appUsers = userService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(appUsers);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/company/all")
    public ResponseEntity<APIResponse> getCompanyDetails() {
        List<CompanyResponse> company = companyService.findAllCompany();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(company);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/profile/all")
    public ResponseEntity<APIResponse> getUserProfile(@PathVariable Long id) {
        List<ProfileResponse> profile = profileService.findAllProfile();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(profile);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
