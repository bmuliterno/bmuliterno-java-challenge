package org.store.api.service;

import org.store.api.domain.*;
import org.store.api.service.CategoryService;
import org.store.api.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Products saveProduct(Products products) {
        return productsRepository.save(products);
    }

    public List<Products> findAllProducts() {
        return productsRepository.findAll();
    }

    public Products findById(Long id) {
        return productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }

    public void removeProduct(Long id) {
        if (!productsRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        productsRepository.deleteById(id);
    }

    public Products associateProductToCategory(Products product, Category category) {
        product.setCategory(category);
        category.getProducts().add(product);
        return productsRepository.save(product);
    }

        public Products disassociateProductFromCategory(Products product) {
        if (product.getCategory() != null) {
            product.getCategory().getProducts().remove(product);
            product.setCategory(null);
        }
        return productsRepository.save(product);
    }
}

