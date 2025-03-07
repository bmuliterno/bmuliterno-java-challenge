package org.store.api.service;

import org.store.api.domain.*;
import org.store.api.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCart saveCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public List<ShoppingCart> findAllItens() {
        return shoppingCartRepository.findAll();
    }

    public void removeFromCart(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
