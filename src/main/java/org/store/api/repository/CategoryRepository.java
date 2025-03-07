package org.store.api.repository;

import org.springframework.stereotype.Repository;
import org.store.api.domain.Category;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CategoryRepository {
    private final Map<Long, Category> categories = new ConcurrentHashMap<>();
    private long nextId = 1;

    public Category save(Category category) {
        if (category.getId() == null) {
            category.setId(nextId++);
        }
        categories.put(category.getId(), category);
        return category;
    }

    public List<Category> findAll() {
        return new ArrayList<>(categories.values());
    }

    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(categories.get(id));
    }

        public void deleteById(Long id) {
        categories.remove(id);
    }
}
