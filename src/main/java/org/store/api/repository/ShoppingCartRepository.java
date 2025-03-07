package org.store.api.repository;

import org.springframework.stereotype.Repository;
import org.store.api.domain.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ShoppingCartRepository {
        private final Map<Long, ShoppingCart> shoppingCart = new ConcurrentHashMap<>();
        private long nextId = 1;

        public ShoppingCart save(ShoppingCart shoppingCart) {
            if (shoppingCart.getId() == null) {
                shoppingCart.setId(nextId++);
            }
            shoppingCart.put(shoppingCart.getId(), shoppingCart);
            return shoppingCart;
        }

        public List<ShoppingCart> findAll() {
            return new ArrayList<>(shoppingCart.values());
        }

        public void deleteById(Long id) {
            shoppingCart.remove(id);
        }
    }
