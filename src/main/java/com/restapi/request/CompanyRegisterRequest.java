package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CompanyRegisterRequest {
    @NotEmpty
    @Size(min = 2, message = "Username should have at least 2 characters")
    private String username;

    @NotEmpty
    @Size(min = 2, message = "Password should have at least 2 characters")
    private String password;

    @NotEmpty
    private String companyType;



    @Column(nullable = false, length = 100)
    private String companyName;

    @Column(nullable = false, length =  100)
    private String companyUrl;

    @Column(nullable = false, length =  200)
    private String aboutCompany;

    @Column(name = "photo")
    private String companyPhoto;

}

//    @NotEmpty
//    @Size(min = 2, message = "Name should have at least 2 characters")
//    private String name;
//    @NotEmpty
//    private String role;
