package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter

public class AppliedRequest {
    private Long id;
    @Size(min = 1,message = "Appuser Id cant Be Empty")
    private Long profileId;
    @Size(min = 1,message = "Job Id must be filled")
    private Long jobId;
    @Size(min = 2,message = "Status could more than 2 character")
    private String status;
}
