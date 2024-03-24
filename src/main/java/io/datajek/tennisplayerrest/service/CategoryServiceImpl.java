package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.CategoryRequest;
import io.datajek.tennisplayerrest.data.dto.response.CategoryResponse;
import io.datajek.tennisplayerrest.data.model.Category;
import io.datajek.tennisplayerrest.data.repository.CategoryRepository;
import io.datajek.tennisplayerrest.exception_handling.PlayerNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public CategoryResponse addCategory(CategoryRequest request) {
        CategoryResponse response = new CategoryResponse();

        Category category = Category.builder()
                .id(0)
                .name(request.getName())
                .build();
        repo.save(category);
        BeanUtils.copyProperties(category, response);

        return response;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<CategoryResponse> responses = new ArrayList<>();

        repo.findAll().stream().forEach(category -> {
            CategoryResponse response = CategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
            responses.add(response);
        });

        return responses;
    }

    @Override
    public CategoryResponse getCategoryById(CategoryRequest request) {
        Optional<Category> tempCategory = repo.findById(request.getId());
        CategoryResponse response = new CategoryResponse();

        if (tempCategory.isEmpty())
            throw new PlayerNotFoundException("Category wiht ID "+ request.getId() +" not found");

        BeanUtils.copyProperties(tempCategory.get(), response);
        return response;
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest request) {
        Optional<Category> tempCategory = repo.findById(request.getId());
        CategoryResponse response = new CategoryResponse();

        if (tempCategory.isEmpty())
            throw new PlayerNotFoundException("Category wiht ID "+ request.getId() +" not found");

        tempCategory.get().setName(request.getName());
        repo.save(tempCategory.get());

        BeanUtils.copyProperties(tempCategory.get(), response);

        return response;
    }

    @Override
    public Category getCategory(CategoryRequest request) {
        Optional<Category> tempCategory = repo.findById(request.getId());

        if (tempCategory.isEmpty())
            throw new PlayerNotFoundException("Category wiht ID "+ request.getId() +" not found");

        return tempCategory.get();
    }

    @Override
    public void deleteCategory(CategoryRequest request) {
        repo.deleteById(request.getId());
    }
}
