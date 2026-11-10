package com.github.statewatcher.product;

import com.github.statewatcher.attribute.Price;
import com.github.statewatcher.attribute.Quantity;
import com.github.statewatcher.attribute.Weight;
import com.github.statewatcher.warehouse.Warehouse;

import java.util.Objects;

public abstract class Product {
    private static int nextId = 0;
    private final int id;
    private final String name;
    private final Price price;
    private final Quantity quantity;
    private final Weight weight;
    private final Category category;
    private Warehouse warehouse;

    protected Product(String name, int price, int quantity, double weight, Category category) {
        this.id = nextId++;
        this.name = name;
        this.price = new Price(price);
        this.quantity = new Quantity(quantity);
        this.weight = new Weight(weight);
        this.category = category;
    }

    protected Product(Product other) {
        this.id = nextId++;
        this.name = other.name;
        this.price = new Price(other.price);
        this.weight = new Weight(other.weight);
        this.quantity = new Quantity(other.quantity);
        this.category = other.category;
        this.warehouse = null;
    }

    public abstract Product clone();

    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public int getPrice() {
        return price.getValue();
    }

    public int getQuantity() {
        return quantity.getValue();
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public String getName() {
        return name;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void changeQuantity(int delta) {
        this.quantity.change(delta);
    }

    public void setPrice(Price newPrice) {
        this.price.setValue(newPrice.getValue());
    }

    public void transferTo(Warehouse targetWarehouse) {
        Objects.requireNonNull(targetWarehouse, "Target warehouse must not be null");
        if (this.warehouse == null) {
            throw new IllegalStateException("Product must be in a warehouse to be transferred");
        }
        if (this.warehouse == targetWarehouse) {
            return;
        }
        if (targetWarehouse.isFull()) {
            throw new IllegalStateException("Target warehouse is full");
        }
        this.warehouse.removeById(this.id);
        targetWarehouse.addProduct(this);
    }
}