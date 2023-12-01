package com.restapi.response;

import com.restapi.model.Category;
import com.restapi.model.Jobs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private Integer count;
    private String lastdate;
    private String postedDate;
    private String categoryId;
    private Long companyId;
}
