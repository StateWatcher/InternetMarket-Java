package com.github.statewatcher.warehouse;

import com.github.statewatcher.product.Category;
import com.github.statewatcher.product.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Warehouse implements Iterable<Product> {
    private ArrayList<Product> products;
    private int maxSize;

    public Warehouse(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Max size must be positive: " + maxSize);
        }
        this.maxSize = maxSize;
        this.products = new ArrayList<>();
    }

    public Warehouse(Warehouse other) {
        this.maxSize = other.maxSize;
        this.products = new ArrayList<>();
        for (Product product : other.products) {
            Product copy = product.clone();
            copy.setWarehouse(this);
            this.products.add(copy);
        }
    }

    public boolean isFull() {
        return this.products.size() >= this.maxSize;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product must not be null");
        }
        if (product.getWarehouse() != null) {
            throw new IllegalArgumentException("Product is already in a warehouse, use transferTo: " + product.getId());
        }
        if (isFull()) {
            throw new IllegalStateException("Max size exceeded, max size is: " + this.maxSize);
        }
        this.products.add(product);
        product.setWarehouse(this);
    }

    public boolean removeById(int id) {
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getId() == id) {
                removeByIndex(i);
                return true;
            }
        }
        return false;
    }

    public void removeByIndex(int index) {
        Product removedProduct = this.products.remove(index);
        if (removedProduct.getWarehouse() == this) {
            removedProduct.setWarehouse(null);
        }
    }

    public Optional<Product> findById(int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public ArrayList<Product> findByCategory(Category category) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product product : this.products) {
            if (product.getCategory() == category) {
                result.add(product);
            }
        }
        return result;
    }

    public ArrayList<Product> findByPrice(int price) {
        return findByPrice(price, price);
    }

    public ArrayList<Product> findByPrice(int minPrice, int maxPrice) {
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Min price must not exceed max price: " + minPrice + " > " + maxPrice);
        }
        ArrayList<Product> result = new ArrayList<>();
        for (Product product : this.products) {
            int price = product.getPrice();
            if (price >= minPrice && price <= maxPrice) {
                result.add(product);
            }
        }
        return result;
    }

    public void transferFrom(Warehouse other) {
        if (this == other) {
            return;
        }
        for (Product product : this.products) {
            product.setWarehouse(null);
        }
        this.products = other.products;
        this.maxSize = other.maxSize;
        other.products = new ArrayList<>();
        for (Product product : this.products) {
            product.setWarehouse(this);
        }
    }

    @Override
    public Iterator<Product> iterator() {
        return new WarehouseIterator();
    }

    private class WarehouseIterator implements Iterator<Product> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < products.size();
        }

        @Override
        public Product next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more products in warehouse");
            }
            return products.get(current++);
        }
    }
}