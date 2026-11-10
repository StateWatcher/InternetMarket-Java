package com.github.statewatcher.product.food;

public class Milk extends Food {
    public Milk(String name, int price, int quantity, double weight) {
        super(name, price, quantity, weight);
    }

    public Milk(Milk other) {
        super(other);
    }

    @Override
    public Milk clone() {
        return new Milk(this);
    }
}
