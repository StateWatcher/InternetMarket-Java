package com.github.statewatcher.product;

public enum Category {
    ELECTRONICS("electronics"),
    CLOTHING("clothing"),
    FOOD("food");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
