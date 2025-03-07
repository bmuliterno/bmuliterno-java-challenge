package org.store.api.service;

import org.springframework.stereotype.Service;
import org.store.api.domain.Products;
import org.store.api.domain.ShoppingCart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartService {
    private final Map<Long, ShoppingCart> carts = new HashMap<>();
    private Long idCounter = 1L;

    public ShoppingCart createCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setId(idCounter++);
        carts.put(cart.getId(), cart);
        return cart;
    }

    public List<Products> listProducts(Long cartId) {
        ShoppingCart cart = findCartById(cartId);
        return cart.getProducts();
    }

    public ShoppingCart addProductToCart(Long cartId, Products product) {
        ShoppingCart cart = findCartById(cartId);
        cart.addProduct(product);
        return cart;
    }

    public ShoppingCart removeProductFromCart(Long cartId, Products product) {
        ShoppingCart cart = findCartById(cartId);
        cart.removeProduct(product);
        return cart;
    }

    private ShoppingCart findCartById(Long cartId) {
        ShoppingCart cart = carts.get(cartId);
        if (cart == null) {
            throw new RuntimeException("Carrinho não encontrado com ID: " + cartId);
        }
        return cart;
    }
}