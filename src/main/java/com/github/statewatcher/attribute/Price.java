package com.github.statewatcher.attribute;

public class Price {
    private int value;


    public Price(int value) {
        this.value = validate(value);
    }

    public Price(Price other) {
        this.value = other.value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = validate(value);
    }

    private static int validate(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Price must be non-negative: " + value);
        }
        return value;
    }

}