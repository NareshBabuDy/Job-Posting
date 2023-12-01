package com.restapi.service;

import com.restapi.dto.CategoryDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.repository.CategoryRepository;
import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryDto categoryDto;

    public CategoryResponse findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryDto.mapToCategoryResponse(categories);
    }

    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = categoryDto.mapToCategory(categoryRequest);
        categoryRepository.save(category);
        return findAll();
    }

    public CategoryResponse update(CategoryRequest categoryRequest) {
        Category category = categoryDto.mapToCategory(categoryRequest);
        categoryRepository.save(category);
        return findAll();
    }

    public CategoryResponse deleteById(Integer id) {
        categoryRepository.deleteById(Long.valueOf(id));
        return findAll();
    }


    public Category findById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow((() -> new ResourceNotFoundException("AppUserId",
                "AppUserId", categoryId)));;
        return category;
    }
}
