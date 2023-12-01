package com.restapi.service;

import com.restapi.dto.ProfileDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Profile;
import com.restapi.repository.ProfileRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.ProfileRequest;
import com.restapi.response.ProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private  StorageService storageService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileDto profileDto;
    public ProfileResponse findById(Long id) {
        Profile profile =  profileRepository.findByUserId(id)
                .orElseThrow((() -> new ResourceNotFoundException("AppUserId",
                        "AppUserId", id)));
        return profileDto.mapToProfileResponse(profile);
    }

//    @Transactional
//    public Profile addProfileDetails(ProfileRequest profileRequest) {
//        Profile profile = ProfileDto.mapToProfileRequest(profileRequest);
//        profile.setProfilePhoto(profileRequest.getProfilePhoto());
//        AppUser appUser = userRepository.findById(profileRequest.getAppUserId())
//                .orElseThrow(() -> new ResourceNotFoundException("AppUserId",
//                        "AppUserId", profileRequest.getAppUserId()));
//        profile.setAppUser(appUser);
//        profileRepository.save(profile);
//        return profile;
//    }

    public List<ProfileResponse> findAllProfile() {
        List<Profile> profiles = profileRepository.findAll();
        List<ProfileResponse> profileResponses = new ArrayList<>();
        for (Profile profile: profiles){
            ProfileResponse profileResponse = profileDto.mapToProfileResponse(profile);
            profileResponses.add(profileResponse);
        }
        return profileResponses;
    }
    public  File getFile(Long id) throws IOException {
        Profile profile= profileRepository.findByUserId(id).orElseThrow((() -> new ResourceNotFoundException("AppUserId",
                "AppUserId", id)));;
        Resource resource = storageService.loadFileAsResource(profile.getResume());
        return resource.getFile();
    }

    public  File getPhoto(Long id) throws IOException {
        Profile profile= profileRepository.findByUserId(id).orElseThrow((() -> new ResourceNotFoundException("AppUserId",
                "AppUserId", id)));
        Resource resource = storageService.loadFileAsResource(profile.getPhoto());
        return resource.getFile();
    }

    public ProfileResponse addProfileDetails(ProfileRequest profileRequest) {
        Profile check = profileRepository.findByUserId(profileRequest.getAppUserId()).orElseThrow((() -> new ResourceNotFoundException("AppUserId",
                "AppUserId", profileRequest.getAppUserId())));
        Profile profile = profileDto.mapToProfileRequest(profileRequest);
        profile.setPhoto(check.getPhoto());
        profile.setResume(check.getResume());
        profile.setAppUser(check.getAppUser());
        profileRepository.save(profile);
        return profileDto.mapToProfileResponse(profile);
    }
}
