package com.restapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private Long Id;
    private String firstName;
    private String lastName;
    private String Gender;
    private String phoneNumber;
    private String email;
    private byte[] profilePhoto;
    private String skills;
    private String experience;
    private Long appUserId;
}
