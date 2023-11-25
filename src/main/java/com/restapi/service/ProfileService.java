package com.restapi.service;

import com.restapi.dto.ProfileDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Company;
import com.restapi.model.Profile;
import com.restapi.repository.ProfileRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.ProfileRequest;
import com.restapi.response.CompanyResponse;
import com.restapi.response.ProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

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

    @Transactional
    public Profile addProfileDetails(ProfileRequest profileRequest) {
        Profile profile = ProfileDto.mapToProfileRequest(profileRequest);
        profile.setProfilePhoto(profileRequest.getProfilePhoto());
        AppUser appUser = userRepository.findById(profileRequest.getAppUserId())
                .orElseThrow(() -> new ResourceNotFoundException("AppUserId",
                        "AppUserId", profileRequest.getAppUserId()));
        profile.setAppUser(appUser);
        profileRepository.save(profile);
        return profile;
    }

    public List<ProfileResponse> findAllProfile() {
        List<Profile> profiles = profileRepository.findAll();
        List<ProfileResponse> profileResponses = new ArrayList<>();
        for (Profile profile: profiles){
            ProfileResponse profileResponse = profileDto.mapToProfileResponse(profile);
            profileResponses.add(profileResponse);
        }
        return profileResponses;
    }
}
