package com.github.statewatcher.factory;

import com.github.statewatcher.product.Product;
import com.github.statewatcher.product.food.Milk;

public class MilkFactory extends ProductFactory {
    @Override
    public Product createProduct(String name, int price, int quantity, double weight) {
        return new Milk(name, price, quantity, weight);
    }
}
