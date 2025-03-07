package org.store.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.store.api.domain.Products;
import org.store.api.domain.Category;
import org.store.api.service.CategoryService;
import org.store.api.service.ProductsService;
import org.store.api.service.ShoppingCartService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductsController {
    private final ProductsService productsService;
    private final CategoryService categoryService;
    private final ShoppingCartService cartService;

    public ProductsController(ProductsService productsService, CategoryService categoryService, ShoppingCartService cartService) {
        this.productsService = productsService;
        this.categoryService = categoryService;
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        return ResponseEntity.ok(productsService.saveProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<Products>> findAll() {
        return ResponseEntity.ok(productsService.findAllProducts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productsService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{productId}/categoria/{categoryId}")
    public ResponseEntity<Products> associateProductToCategory(@PathVariable Long productId, @PathVariable Long categoryId) {
        Products products = productsService.findById(productId);
        Category category = categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(productsService.associateProductToCategory(products, category));
    }

    @PostMapping("/{productId}/categoria/remover")
    public ResponseEntity<Products> disassociateProductFromCategory(@PathVariable Long productId) {
        Products product = productsService.findById(productId);
        return ResponseEntity.ok(productsService.disassociateProductFromCategory(product));
    }
}
