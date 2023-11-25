package com.restapi.service;

import com.restapi.dto.CompanyDto;
import com.restapi.exception.common.InvalidUserException;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Company;
import com.restapi.model.Profile;
import com.restapi.repository.CompanyRepository;
import com.restapi.request.CompanyRequest;
import com.restapi.response.CompanyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CompanyService {

    @Autowired
    private CompanyDto companyDto;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyResponse> findAllCompany() {
        List<Company> company = companyRepository.findAll();
        List<CompanyResponse> companyResponse = new ArrayList<>();
        for (Company company1 : company) {
            CompanyResponse companyResponse1 = companyDto.mapToCompanyResponse(company1);
            companyResponse.add(companyResponse1);
        }
        return companyResponse;
    }

    public CompanyResponse createCompanyDetails(CompanyRequest companyRequest) {
        Company company = companyDto.mapToCompany(companyRequest);
        AppUser appUser = userService.findUserById(companyRequest.getAppUserId());
        company.setAppUser(appUser);
        companyRepository.save(company);

        return companyDto.mapToCompanyResponse(company);
    }

    public CompanyResponse findByCompanyId(Long id) {
        Company company =  companyRepository.findByCompanyId(id)
                .orElseThrow((() -> new ResourceNotFoundException("AppUserId",
                        "AppUserId", id)));
        return companyDto.mapToCompanyResponse(company);
    }
}
