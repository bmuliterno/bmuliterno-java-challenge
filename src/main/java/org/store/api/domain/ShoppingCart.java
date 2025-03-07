package org.store.api.domain;

import java.util.List;

public class ShoppingCart {
    private Long id;
    private List<Products> products;

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void put(Long id, ShoppingCart shoppingCart) {
    }
}
