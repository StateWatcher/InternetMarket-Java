package com.github.statewatcher.factory;

import com.github.statewatcher.product.Product;
import com.github.statewatcher.product.clothing.Jacket;

public class JacketFactory extends ProductFactory {
    @Override
    public Product createProduct(String name, int price, int quantity, double weight) {
        return new Jacket(name, price, quantity, weight);
    }
}
