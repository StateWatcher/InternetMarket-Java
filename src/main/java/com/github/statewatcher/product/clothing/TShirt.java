package com.github.statewatcher.product.clothing;

public class TShirt extends Clothing {
    public TShirt(String name, int price, int quantity, double weight) {
        super(name, price, quantity, weight);
    }
    public TShirt(TShirt other) {
        super(other);
    }

    @Override
    public TShirt clone() {
        return new TShirt(this);
    }
}
