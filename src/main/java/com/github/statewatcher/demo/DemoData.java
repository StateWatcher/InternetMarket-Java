package com.github.statewatcher.demo;

import com.github.statewatcher.factory.BreadFactory;
import com.github.statewatcher.factory.JacketFactory;
import com.github.statewatcher.factory.LaptopFactory;
import com.github.statewatcher.factory.MilkFactory;
import com.github.statewatcher.factory.SmartphoneFactory;
import com.github.statewatcher.factory.TShirtFactory;
import com.github.statewatcher.product.Product;

public class DemoData {

    public static Product create(String key) {
        return switch (key) {
            case "phone" -> new SmartphoneFactory().createProduct("Смартфон Pixel 9", 79990, 10, 0.2);
            case "laptop" -> new LaptopFactory().createProduct("Ноутбук ThinkPad", 149990, 5, 1.1);
            case "tshirt" -> new TShirtFactory().createProduct("Футболка базовая", 1490, 50, 0.15);
            case "jacket" -> new JacketFactory().createProduct("Зимняя парка", 12990, 8, 1.8);
            case "milk" -> new MilkFactory().createProduct("Молоко 3.2%", 99, 40, 1.0);
            case "bread" -> new BreadFactory().createProduct("Хлеб ржаной", 69, 30, 0.5);
            case "kefir" -> new MilkFactory().createProduct("Кефир 1%", 89, 20, 1.0);
            default -> throw new IllegalArgumentException("Нет товара с ключом " + key);
        };
    }
}
