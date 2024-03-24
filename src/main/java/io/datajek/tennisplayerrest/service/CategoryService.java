package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.CategoryRequest;
import io.datajek.tennisplayerrest.data.dto.response.CategoryResponse;
import io.datajek.tennisplayerrest.data.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest request);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(CategoryRequest request);
    CategoryResponse updateCategory(CategoryRequest request);
    Category getCategory(CategoryRequest request);
    void deleteCategory(CategoryRequest request);
}