package com.github.statewatcher.product.electronics;

import com.github.statewatcher.product.Category;
import com.github.statewatcher.product.Product;

public abstract class Electronics extends Product {
    protected Electronics(String name, int price, int quantity, double weight) {
        super(name, price, quantity, weight, Category.ELECTRONICS);
    }

    protected Electronics(Electronics other) {
        super(other);
    }
}
