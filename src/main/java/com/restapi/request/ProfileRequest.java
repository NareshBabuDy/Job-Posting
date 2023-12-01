package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ProfileRequest {
    private Long Id;
    @Size(min = 3, message = "first name should be min 3 letter")
    private String firstName;
    @Size(min = 3, message = "Last name should be min 3 letter")
    private String lastName;
    @Size(min = 3, message = "gender should be min 3 letter")
    private String gender;
    @Size(min = 9, message = "number have min 9 digits")
    private String phoneNumber;
    @Size(min = 6, message = "email should be min 6 letter")
    private String email;
    @Size(min = 3, message = "skills should be min 3 letter")
    private String skills;
    @Size(min = 1, message = "expirience should be min 3 letter")
    private String experience;
    @Size(min = 1, message = "AppUserId Should be filled")
    private Long appUserId;
}
