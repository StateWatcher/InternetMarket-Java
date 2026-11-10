package com.github.statewatcher.product.electronics;

public class Laptop extends Electronics {
    public Laptop(String name, int price, int quantity, double weight) {
        super(name, price, quantity, weight);
    }

    public Laptop(Laptop other) {
        super(other);
    }

    @Override
    public Laptop clone() {
        return new Laptop(this);
    }
}
