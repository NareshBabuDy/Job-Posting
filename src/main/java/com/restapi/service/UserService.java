package com.restapi.service;

import com.restapi.dto.AuthDto;
import com.restapi.dto.CompanyDto;
import com.restapi.dto.ProfileDto;
import com.restapi.exception.common.InvalidUserException;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Company;
import com.restapi.model.Profile;
import com.restapi.model.Role;
import com.restapi.repository.CompanyRepository;
import com.restapi.repository.ProfileRepository;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CompanyRegisterRequest;
import com.restapi.request.LoginRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthDto authDto;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyDto companyDto;

    @Autowired
    private  UserService userService;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthResponse login(LoginRequest loginRequest) {
        AppUser appUser = userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidUserException("Invalid user credentials"));

        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {
            throw new InvalidUserException("Invalid password");
        }

        return authDto.mapToAuthResponse(appUser);
    }

    public List<AuthResponse> findAll() {
        List<AppUser> appUser = userRepository.findAll();
        List<AuthResponse> authResponseList = new ArrayList<>();
        for (AppUser user : appUser){
            AuthResponse authResponse = authDto.mapToAuthResponse(user);
            authResponseList.add(authResponse);
        }
        return authResponseList;
    }

    public AppUser findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
    }

//    public AppUser candidateRegister(String username, String password, String name, String role) {
//        AppUser user = new AppUser();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setRoles(roleRepository.findByName(role));
//        user.setName(name);
//        userRepository.save(user);
//        return user;
//    }

    public AuthResponse candidateRegister(RegisterRequest registerRequest) {
        AppUser appUser = new AppUser();
        appUser.setUsername(registerRequest.getUsername());
        appUser.setName(registerRequest.getFirstName()+registerRequest.getLastName());
        appUser.setPassword(registerRequest.getPassword()); appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(roleRepository.findByName(Role.USER));
        appUser = userRepository.save(appUser);
        Profile profile = new Profile();
        profile.setFirstName(registerRequest.getFirstName());
        profile.setLastName(registerRequest.getLastName());
        profile.setGender(registerRequest.getGender());
        profile.setPhoto(registerRequest.getPhoto());
        profile.setPhoneNumber(registerRequest.getPhoneNumber());
        profile.setEmail(registerRequest.getEmail());
        profile.setResume(registerRequest.getResume());
        profile.setSkills(registerRequest.getSkills());
        profile.setExperience(registerRequest.getExperience());
        profile.setAppUser(appUser);
        profileRepository.save(profile);

        return authDto.mapToAuthResponse(appUser);
    }

        public AuthResponse register(CompanyRegisterRequest registerRequest) {
            AppUser appUser = new AppUser();
            appUser.setUsername(registerRequest.getUsername());
            appUser.setName(registerRequest.getCompanyName());
            appUser.setPassword(registerRequest.getPassword()); appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
            appUser.setRoles(roleRepository.findByName(Role.RECRUITER));
            appUser = userRepository.save(appUser);
            Company company = new Company();
            company.setAboutCompany(registerRequest.getAboutCompany());
            company.setCompanyName(registerRequest.getCompanyName());
            company.setCompanyUrl(registerRequest.getCompanyUrl());
            company.setCompanyPhoto(registerRequest.getCompanyPhoto());
            company.setCompanyType(registerRequest.getCompanyType());
            company.setAppUser(appUser);
            companyRepository.save(company);
            return authDto.mapToAuthResponse(appUser);
        }

}
