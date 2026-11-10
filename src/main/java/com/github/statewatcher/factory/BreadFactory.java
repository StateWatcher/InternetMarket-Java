package com.github.statewatcher.factory;

import com.github.statewatcher.product.Product;
import com.github.statewatcher.product.food.Bread;

public class BreadFactory extends ProductFactory{
    @Override
    public Product createProduct(String name, int price, int quantity, double weight) {
        return new Bread(name, price, quantity, weight);
    }
}
