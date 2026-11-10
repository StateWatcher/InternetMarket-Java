package com.github.statewatcher.factory;

import com.github.statewatcher.product.Product;
import com.github.statewatcher.product.clothing.TShirt;

public class TShirtFactory extends ProductFactory {
    @Override
    public Product createProduct(String name, int price, int quantity, double weight) {
        return new TShirt(name, price, quantity, weight);
    }
}
