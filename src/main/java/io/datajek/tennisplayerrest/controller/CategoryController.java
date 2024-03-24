package io.datajek.tennisplayerrest.controller;

import io.datajek.tennisplayerrest.data.dto.request.CategoryRequest;
import io.datajek.tennisplayerrest.data.dto.response.CategoryResponse;
import io.datajek.tennisplayerrest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("/categories")
    public CategoryResponse addCategory(@RequestBody CategoryRequest request){
        return service.addCategory(request);
    }

    @GetMapping("/categories/{id}")
    public CategoryResponse getCategory(CategoryRequest request){
        return service.getCategoryById(request);
    }

    @GetMapping("/categories")
    public List<CategoryResponse> getAllCategories(){
        return service.getAllCategories();
    }

    @PatchMapping("/categories/{id}")
    public CategoryResponse updateCategory(@RequestBody CategoryRequest request){
        return service.updateCategory(request);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(CategoryRequest request){
        service.deleteCategory(request);
    }
}
