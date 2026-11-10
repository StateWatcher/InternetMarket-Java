package com.github.statewatcher.attribute;

public class Weight {
    private double value;


    public Weight(double value) {
        this.value = validate(value);
    }

    public Weight(Weight other) {
        this.value = other.value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = validate(value);
    }

    private static double validate(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Weight must be finite:" + value);
        }
        if (value < 0) {
            throw new IllegalArgumentException("Weight must be non-negative :" + value);
        }
        return value;
    }

}
