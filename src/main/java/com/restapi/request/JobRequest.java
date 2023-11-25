package com.restapi.request;

import com.restapi.model.Category;
import com.restapi.model.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    private Long id;
    @Size(min = 4, message = "Job Title Should have more than 4 character")
    private String title;
    @Size(min = 4, message = "Job Description Should have more than 4 character")
    private String description;
    @Size(min = 1, message = "Job Count should be minimum 1")
    private Integer count;
    private String lastdate;
    @Size(min = 1, message = "Category id Should not be empty")
    private Long category_id;
    @Size(min = 1, message = "Company Id Should not be empty")
    private Long company;
}
