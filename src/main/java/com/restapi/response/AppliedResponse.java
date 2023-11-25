package com.restapi.response;

import com.restapi.model.AppUser;
import com.restapi.model.Applied;
import com.restapi.model.Jobs;
import com.restapi.model.Profile;
import com.restapi.request.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppliedResponse {
    private Long ApplicationId;
    private Profile profile;
    private Jobs jobDetails;
    private String status;
}
