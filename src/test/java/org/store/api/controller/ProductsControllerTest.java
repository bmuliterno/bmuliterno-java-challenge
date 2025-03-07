package org.store.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.store.api.domain.Category;
import org.store.api.domain.Products;
import org.store.api.service.CategoryService;
import org.store.api.service.ProductsService;
import org.store.api.service.ShoppingCartService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductsControllerTest {

    @InjectMocks
    private ProductsController productsController;

    @Mock
    private ProductsService productsService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ShoppingCartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Products product = new Products();
        product.setId(1L);
        product.setName("Notebook");
        product.setPrice(5000.0);

        when(productsService.saveProduct(any(Products.class))).thenReturn(product);

        ResponseEntity<Products> response = productsController.createProduct(product);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Notebook", response.getBody().getName());
        assertEquals(5000.0, response.getBody().getPrice());
        verify(productsService, times(1)).saveProduct(any(Products.class));
    }

    @Test
    void testFindAll() {
        Products product1 = new Products(1L, "Notebook", 5000.0, null);
        Products product2 = new Products(2L, "Celular", 1500.0, null);

        when(productsService.findAllProducts()).thenReturn(Arrays.asList(product1, product2));

        ResponseEntity<List<Products>> response = productsController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(productsService, times(1)).findAllProducts();
    }

    @Test
    void testDeleteProduct() {
        ResponseEntity<Void> response = productsController.deleteProduct(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(productsService, times(1)).removeProduct(1L);
    }

    @Test
    void testDisassociateProductFromCategory() {
        Products product = new Products(1L, "Notebook", 5000.0, null);

        when(productsService.findById(1L)).thenReturn(product);
        when(productsService.disassociateProductFromCategory(product)).thenReturn(product);

        ResponseEntity<Products> response = productsController.disassociateProductFromCategory(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Notebook", response.getBody().getName());
        assertEquals(null, response.getBody().getCategory());
        verify(productsService, times(1)).disassociateProductFromCategory(product);
    }
}