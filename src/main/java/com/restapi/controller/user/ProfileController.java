package com.restapi.controller.user;

import com.restapi.model.Profile;
import com.restapi.model.Role;
import com.restapi.request.ProfileRequest;
import com.restapi.response.ProfileResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/profile")
@RolesAllowed({Role.USER})
public class ProfileController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUserProfile(@PathVariable Long id) {
        ProfileResponse profile = profileService.findById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(profile);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> addProfileDetails(@RequestBody ProfileRequest profileRequest) {
        Profile profile = profileService.addProfileDetails(profileRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(profile);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateProfileDetails(@RequestBody ProfileRequest profileRequest) {
        Profile profile = profileService.addProfileDetails(profileRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(profile);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
