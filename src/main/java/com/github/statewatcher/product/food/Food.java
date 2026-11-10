package com.github.statewatcher.product.food;

import com.github.statewatcher.product.Category;
import com.github.statewatcher.product.Product;

public abstract class Food extends Product {
    protected Food(String name, int price, int quantity, double weight) {
        super(name, price, quantity, weight, Category.FOOD);
    }

    protected Food(Food other) {
        super(other);
    }
}
