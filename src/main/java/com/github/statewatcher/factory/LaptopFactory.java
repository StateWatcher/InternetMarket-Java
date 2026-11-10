package com.github.statewatcher.factory;

import com.github.statewatcher.product.Product;
import com.github.statewatcher.product.electronics.Laptop;

public class LaptopFactory extends ProductFactory {
    @Override
    public Product createProduct(String name, int price, int quantity, double weight) {
        return new Laptop(name, price, quantity, weight);
    }
}
