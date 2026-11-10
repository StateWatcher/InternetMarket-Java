package com.github.statewatcher.factory;

import com.github.statewatcher.product.Product;

public abstract class ProductFactory {
    public abstract Product createProduct(String name, int price, int quantity, double weight);
}
