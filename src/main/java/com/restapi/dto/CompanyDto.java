package com.restapi.dto;

import com.restapi.model.Company;
import com.restapi.request.CompanyRequest;
import com.restapi.response.CompanyResponse;
import org.springframework.stereotype.Component;

@Component
public class CompanyDto {

    public Company mapToCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        if (companyRequest.getId() != null) {
            company.setId(companyRequest.getId());
        }
        company.setCompanyName(companyRequest.getCompanyName());
        company.setCompanyUrl(companyRequest.getCompanyUrl());
        company.setCompanyPhoto(companyRequest.getPhoto());

        return company;
    }

    public CompanyResponse mapToCompanyResponse(Company company) {
        CompanyResponse companyResponse= new CompanyResponse();
        companyResponse.setId(company.getId());
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setCompanyUrl(company.getCompanyUrl());
        companyResponse.setAppUser(company.getAppUser().getId());
        companyResponse.setPhoto(company.getCompanyPhoto());
        return companyResponse;
    }
}
