package org.store.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.store.api.domain.Products;
import org.store.api.domain.ShoppingCart;
import org.store.api.service.CategoryService;
import org.store.api.service.ProductsService;
import org.store.api.service.ShoppingCartService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ShoppingCartControllerTest {

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @Mock
    private ShoppingCartService cartService;

    @Mock
    private ProductsService productsService;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setId(1L);

        when(cartService.createCart()).thenReturn(cart);

        ResponseEntity<ShoppingCart> response = shoppingCartController.createCart();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(cartService, times(1)).createCart();
    }

    @Test
    void testListProducts() {
        Products product1 = new Products(1L, "Notebook", 5000.0, null);
        Products product2 = new Products(2L, "Celular", 1500.0, null);

        when(cartService.listProducts(1L)).thenReturn(Arrays.asList(product1, product2));

        ResponseEntity<List<Products>> response = shoppingCartController.listProducts(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(cartService, times(1)).listProducts(1L);
    }

    @Test
    void testAddProduct() {
        Products product = new Products(1L, "Notebook", 5000.0, null);
        ShoppingCart cart = new ShoppingCart();
        cart.setId(1L);
        cart.setProducts(Arrays.asList(product));

        when(cartService.addProductToCart(1L, product)).thenReturn(cart);

        ResponseEntity<ShoppingCart> response = shoppingCartController.addProduct(1L, product);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        assertEquals(1, response.getBody().getProducts().size());
        verify(cartService, times(1)).addProductToCart(1L, product);
    }

    @Test
    void testRemoveProduct() {
        Products product = new Products(1L, "Notebook", 5000.0, null);
        ShoppingCart cart = new ShoppingCart();
        cart.setId(1L);

        when(cartService.removeProductFromCart(1L, product)).thenReturn(cart);

        ResponseEntity<ShoppingCart> response = shoppingCartController.removeProduct(1L, product);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(cartService, times(1)).removeProductFromCart(1L, product);
    }
}
