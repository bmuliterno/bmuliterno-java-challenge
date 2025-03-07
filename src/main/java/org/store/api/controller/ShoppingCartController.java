package org.store.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.store.api.domain.Products;
import org.store.api.domain.ShoppingCart;
import org.store.api.service.CategoryService;
import org.store.api.service.ProductsService;
import org.store.api.service.ShoppingCartService;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
public class ShoppingCartController {
    private final ShoppingCartService cartService;
    private final ProductsService productsService;
    private final CategoryService categoryService;

    public ShoppingCartController(ShoppingCartService cartService, ProductsService productsService, CategoryService categoryService) {
        this.cartService = cartService;
        this.productsService = productsService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> createCart() {
        return ResponseEntity.ok(cartService.createCart());
    }

    @GetMapping("/{cartId}/produtos")
    public ResponseEntity<List<Products>> listProducts(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.listProducts(cartId));
    }

    @PostMapping("/{cartId}/produtos")
    public ResponseEntity<ShoppingCart> addProduct(@PathVariable Long cartId, @RequestBody Products product) {
        return ResponseEntity.ok(cartService.addProductToCart(cartId, product));
    }

    @DeleteMapping("/{cartId}/produtos")
    public ResponseEntity<ShoppingCart> removeProduct(@PathVariable Long cartId, @RequestBody Products product) {
        return ResponseEntity.ok(cartService.removeProductFromCart(cartId, product));
    }
}
