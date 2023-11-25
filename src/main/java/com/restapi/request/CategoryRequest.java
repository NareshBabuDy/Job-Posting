package com.restapi.request;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryRequest {
    private Long id;
    @Size(min = 5, message = "category Name should be more than 5 character")
    private String title;

    public CategoryRequest(String title) {
        this.title = title;
    }
}