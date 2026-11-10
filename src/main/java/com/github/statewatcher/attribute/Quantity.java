package com.github.statewatcher.attribute;

public class Quantity {
    private int value;


    public Quantity(int value) {
        this.value = validate(value);
    }

    public Quantity(Quantity other) {
        this.value = other.value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = validate(value);
    }

    public void change(int delta) {
        this.value = validate(this.value + delta);
    }

    private static int validate(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative:" + value);
        }
        return value;
    }

}
