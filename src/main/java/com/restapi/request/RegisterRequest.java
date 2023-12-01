package com.restapi.request;

import com.restapi.model.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest {

    @NotEmpty
    @Size(min = 2, message = "Username should have at least 2 characters")
    private String username;
    @NotEmpty
    @Size(min = 2, message = "Password should have at least 2 characters")
    private String password;
    private String firstName;
    private String lastName;
    private String Gender;
    private String phoneNumber;
    private String email;
    private String resume;
    private String photo;
    private String skills;
    private String experience;


}
