package com.github.statewatcher.product.clothing;

import com.github.statewatcher.product.Category;
import com.github.statewatcher.product.Product;

public abstract class Clothing extends Product {
    protected Clothing(String name, int price, int quantity, double weight) {
        super(name, price, quantity, weight, Category.CLOTHING);
    }
    protected Clothing(Clothing other) {
        super(other);
    }
}
