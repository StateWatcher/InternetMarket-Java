package com.github.statewatcher.product.electronics;

public class Smartphone extends Electronics {
    public Smartphone(String name, int price, int quantity, double weight) {
        super(name, price, quantity, weight);
    }

    public Smartphone(Smartphone other) {
        super(other);
    }

    @Override
    public Smartphone clone() {
        return new Smartphone(this);
    }
}
