package com.github.statewatcher.product.clothing;

public class Jacket extends Clothing {
    public Jacket(String name, int price, int quantity, double weight) {
        super(name, price, quantity, weight);
    }

    public Jacket(Jacket other) {
        super(other);
    }

    @Override
    public Jacket clone() {
        return new Jacket(this);
    }
}
