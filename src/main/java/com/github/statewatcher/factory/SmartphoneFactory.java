package com.github.statewatcher.factory;

import com.github.statewatcher.product.Product;
import com.github.statewatcher.product.electronics.Smartphone;

public class SmartphoneFactory extends ProductFactory {
    @Override
    public Product createProduct(String name, int price, int quantity, double weight) {
        return new Smartphone(name, price, quantity, weight);
    }
}
