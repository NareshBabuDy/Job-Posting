package com.restapi.request;

import com.restapi.model.AppUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CompanyRequest {
    private Long id;
    @Size(min = 4, message = "Company Name Should have more than 4 character")
    private String companyName;
    @Size(min = 4, message = "Company url Should have more than 4 character")
    private String companyUrl;
    @Size(min = 1, message = "AppUserId Should be filled")
    private Long appUserId;
    private byte[] photo;
}
