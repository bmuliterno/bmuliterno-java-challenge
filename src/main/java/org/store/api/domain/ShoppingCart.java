package org.store.api.domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private Long id;
    private List<Products> products;
    private Double totalValue;

    public ShoppingCart() {
        this.products = new ArrayList<>();
        this.totalValue = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
        calculateTotalValue();
    }

    public Double getTotalValue() {
        return totalValue;
    }

    private void calculateTotalValue() {
        this.totalValue = products.stream()
                .mapToDouble(Products::getPrice)
                .sum();
    }

    public void addProduct(Products product) {
        this.products.add(product);
        this.totalValue += product.getPrice();
    }

    public void removeProduct(Products product) {
        this.products.remove(product);
        this.totalValue -= product.getPrice();
    }
}
