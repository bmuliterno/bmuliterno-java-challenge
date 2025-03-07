package org.store.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.store.api.domain.Category;
import org.store.api.service.CategoryService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCategory() {
        Category mockCategory = new Category();
        mockCategory.setId(1L);
        mockCategory.setName("Eletrônicos");

        when(categoryService.saveCategory(any(Category.class))).thenReturn(mockCategory);

        ResponseEntity<Category> response = categoryController.createCategory(mockCategory);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Eletrônicos", response.getBody().getName());
        verify(categoryService, times(1)).saveCategory(mockCategory);
    }

    @Test
    void testFindAll() {
        // Mock da lista de categorias
        Category category1 = new Category(1L, "Eletrônicos");
        Category category2 = new Category(2L, "Alimentos");

        when(categoryService.findAllCategories()).thenReturn(Arrays.asList(category1, category2));

        ResponseEntity<List<Category>> response = categoryController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(categoryService, times(1)).findAllCategories();
    }

    @Test
    void testDeleteCategory() {
        ResponseEntity<Void> response = categoryController.deleteCategory(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(categoryService, times(1)).removeCategory(1L);
    }
}
