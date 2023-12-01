package com.restapi.dto;

import com.restapi.model.Profile;
import com.restapi.request.ProfileRequest;
import com.restapi.response.ProfileResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileDto {
    public ProfileResponse mapToProfileResponse(Profile profile) {
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setId(profile.getId());
        profileResponse.setFirstName(profile.getFirstName());
        profileResponse.setLastName(profile.getLastName());
        profileResponse.setGender(profile.getGender());
        profileResponse.setPhoneNumber(profile.getPhoneNumber());
        profileResponse.setEmail(profile.getEmail());
        profileResponse.setPhoto(profileResponse.getPhoto());
        profileResponse.setSkills(profile.getSkills());
        profileResponse.setExperience(profile.getExperience());
        profileResponse.setAppUserId(profile.getAppUser().getId());
        return profileResponse;
    }

    public  Profile mapToProfileRequest(ProfileRequest profileRequest) {
        Profile profile = new Profile();
        if (profileRequest.getId() != null) {
            profile.setId(profileRequest.getId());
        }
        profile.setFirstName(profileRequest.getFirstName());
        profile.setLastName(profileRequest.getLastName());
        profile.setGender(profileRequest.getGender());
        profile.setPhoneNumber(profileRequest.getPhoneNumber());
        profile.setEmail(profileRequest.getEmail());
        profile.setSkills(profileRequest.getSkills());
        profile.setExperience(profileRequest.getExperience());

        return profile;
    }
}
