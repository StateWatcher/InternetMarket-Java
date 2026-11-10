package com.github.statewatcher.product.food;

public class Bread extends Food {
    public Bread(String name, int price, int quantity, double weight) {
        super(name, price, quantity, weight);
    }

    public Bread(Bread other) {
        super(other);
    }

    @Override
    public Bread clone() {
        return new Bread(this);
    }
}
