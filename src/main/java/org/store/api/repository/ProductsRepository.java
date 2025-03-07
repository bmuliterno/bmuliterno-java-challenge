package org.store.api.repository;

import org.springframework.stereotype.Repository;
import org.store.api.domain.Products;

import java.util.*;

@Repository
public class ProductsRepository {
    private final Map<Long, Products> productsMap = new HashMap<>();
    private Long idCounter = 1L;

    public Products save(Products product) {
        if (product.getId() == null) {
            product.setId(idCounter++);
        }
        productsMap.put(product.getId(), product);
        return product;
    }

    public List<Products> findAll() {
        return new ArrayList<>(productsMap.values());
    }

    public Optional<Products> findById(Long id) {
        return Optional.ofNullable(productsMap.get(id));
    }

    public void deleteById(Long id) {
        productsMap.remove(id);
    }

    public boolean existsById(Long id) {
        return productsMap.containsKey(id);
    }
}
