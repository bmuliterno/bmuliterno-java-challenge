package org.store.api.repository;

import org.springframework.stereotype.Repository;
import org.store.api.domain.Products;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductRepository {
        private final Map<Long, Products> products = new ConcurrentHashMap<>();
        private long nextId = 1;

        public Products save(Products products) {
            if (products.getId() == null) {
                products.setId(nextId++);
            }
            products.put(products.getId(), products);
            return products;
        }

        public List<Products> findAll() {
            return new ArrayList<>(products.values());
        }

        public void deleteById(Long id) {
            products.remove(id);
        }
    }
