package com.github.statewatcher.demo;

import com.github.statewatcher.product.Product;
import com.github.statewatcher.warehouse.Warehouse;

import java.util.List;

public class ConsolePrinter {

    public static void printWarehouse(Warehouse warehouse) {
        int count = 0;
        for (Product product : warehouse) {
            printProduct(product);
            count++;
        }
        if (count == 0) {
            System.out.println("  склад пуст");
        }
    }

    public static void printList(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("  ничего не найдено");
        }
        for (Product product : products) {
            printProduct(product);
        }
    }

    public static void printProduct(Product product) {
        if (product == null) {
            System.out.println("  не найден");
            return;
        }
        System.out.printf("  #%-3d %-18s %-12s цена: %d, кол-во: %d%n",
                product.getId(), product.getName(), product.getCategory().getValue(),
                product.getPrice(), product.getQuantity());
    }
}
