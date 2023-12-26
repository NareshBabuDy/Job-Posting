package com.restapi.controller;

import com.restapi.request.LoginRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.request.CompanyRegisterRequest;
import com.restapi.service.ProfileService;
import com.restapi.service.StorageService;
import com.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse loggedInUser = userService.login(loginRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(loggedInUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/company/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> register(@RequestParam("companyPhoto") MultipartFile photo, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("companyName") String companyName, @RequestParam("companyUrl") String companyUrl, @RequestParam("aboutCompany") String aboutCompany, @RequestParam("companyType") String companyType) throws IOException {
        String companyPhoto = storageService.storeFile(photo);
        CompanyRegisterRequest registerRequest = new CompanyRegisterRequest();
        registerRequest.setUsername(username);
        registerRequest.setPassword(password);
        registerRequest.setCompanyName(companyName);
        registerRequest.setCompanyType(companyType);
        registerRequest.setAboutCompany(aboutCompany);
        registerRequest.setCompanyUrl(companyUrl);
        registerRequest.setCompanyPhoto(companyPhoto);
        AuthResponse registeredUser = userService.register(registerRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(registeredUser);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    @PostMapping("/candidate/register")
//    public ResponseEntity<APIResponse> candidateRegister(@Valid @RequestBody RegisterRequest registerRequest) {
//        AuthResponse registeredUser = userService.candidateRegister(registerRequest);
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setData(registeredUser);
//
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> addProfile(@RequestParam("resume") MultipartFile resume, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("gender") String gender, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("email") String email, @RequestParam("skills") String skills, @RequestParam("experience") String experience, @RequestParam("photo") MultipartFile photo) throws IOException {
        String userResume = storageService.storeFile(resume);
        String userPhoto = storageService.storeFile(photo);
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(username);
        registerRequest.setPassword(password);
        registerRequest.setFirstName(firstName);
        registerRequest.setLastName(lastName);
        registerRequest.setGender(gender);
        registerRequest.setPhoneNumber(phoneNumber);
        registerRequest.setEmail(email);
        registerRequest.setResume(userResume);
        registerRequest.setPhoto(userPhoto);
        registerRequest.setSkills(skills);
        registerRequest.setExperience(experience);
        AuthResponse user = userService.candidateRegister(registerRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(user);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
