package org.store.api.service;

import org.store.api.domain.*;
import org.store.api.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductsService {
    private final ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Products saveProduct(Products products) {
        return productRepository.save(products);
    }

    public List<Products> findAllProducts() {
        return productRepository.findAll();
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }
}

