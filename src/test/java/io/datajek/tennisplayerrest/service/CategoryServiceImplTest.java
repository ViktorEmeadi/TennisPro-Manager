package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.CategoryRequest;
import io.datajek.tennisplayerrest.data.dto.response.CategoryResponse;
import io.datajek.tennisplayerrest.data.model.Category;
import io.datajek.tennisplayerrest.data.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {
    CategoryRepository repo;
    CategoryService service;

    @BeforeEach
    void init(){
        repo = mock(CategoryRepository.class);
        service = new CategoryServiceImpl(repo);
    }

    @Test
    void addCategoryTest(){
        CategoryRequest request = CategoryRequest.builder()
                .name("Relay")
                .build();
        when(repo.save(any(Category.class))).thenAnswer(invocationOnMock -> {
            Category saved = invocationOnMock.getArgument(0);
            saved.setId(1);
            return saved;
        });

        CategoryResponse response = service.addCategory(request);

        assertEquals(1, response.getId());
        assertEquals("Relay", response.getName());
    }

    @Test
    void geAllCategoriesTest(){
        Category category1 = Category.builder()
                .id(1)
                .name("Relay")
                .build();
        Category category2 = Category.builder()
                .name("100m")
                .build();
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        when(repo.findAll()).thenReturn(categories);

        List<CategoryResponse> response = service.getAllCategories();

        assertEquals(categories.get(0).getId(), response.get(0).getId());
        assertEquals(categories.get(1).getId(), response.get(1).getId());
    }

    @Test
    void  getCategoryByIdTest(){
        Category category = Category.builder()
                .id(1)
                .name("Relay")
                .build();

        when(repo.findById(1)).thenReturn(Optional.ofNullable(category));

        CategoryRequest request = CategoryRequest.builder().id(1).build();
        CategoryResponse response = service.getCategoryById(request);

        assertEquals(1, response.getId());
        assertEquals("Relay", response.getName());
    }

    @Test
    void updateCategoryTest(){
        Category category = Category.builder()
                .id(1)
                .name("Relay")
                .build();

        when(repo.save(category)).thenReturn(category);
        when(repo.findById(1)).thenReturn(Optional.of(category));

        CategoryRequest request = CategoryRequest.builder().id(1).name("100m").build();
        CategoryResponse response = service.updateCategory(request);

        assertEquals(1, response.getId());
        assertEquals("100m", response.getName());
    }
}