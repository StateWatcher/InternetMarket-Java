package com.github.statewatcher;

import com.github.statewatcher.attribute.Price;
import com.github.statewatcher.demo.ConsolePrinter;
import com.github.statewatcher.demo.DemoData;
import com.github.statewatcher.product.Category;
import com.github.statewatcher.product.Product;
import com.github.statewatcher.warehouse.Warehouse;

public class Main {
    public static void main(String[] args) {
        testAddAndIterator();
        testCopy();
        testTransferFrom();
        testMove();
        testSearchAndRemove();
        testProduct();
    }

    private static void testAddAndIterator() {
        System.out.println("1. Добавление и итератор");
        Warehouse warehouse = new Warehouse(6);
        System.out.println("Пустой склад, лимит 6:");
        ConsolePrinter.printWarehouse(warehouse);

        String[] keys = {"phone", "laptop", "tshirt", "jacket", "milk", "bread"};
        Product last = null;
        for (String key : keys) {
            last = DemoData.create(key);
            warehouse.addProduct(last);
            System.out.println("addProduct(" + key + "), товар привязан к складу: " + (last.getWarehouse() == warehouse));
        }
        System.out.println("Склад после добавления:");
        ConsolePrinter.printWarehouse(warehouse);

        Product kefir = DemoData.create("kefir");
        try {
            warehouse.addProduct(kefir);
        } catch (IllegalStateException e) {
            System.out.println("addProduct(kefir) -> " + e.getMessage());
        }

        warehouse.removeById(last.getId());
        System.out.println("Удалили хлеб, освободилось место");
        warehouse.addProduct(kefir);
        System.out.println("addProduct(kefir) после удаления:");
        ConsolePrinter.printWarehouse(warehouse);

        for (Product product : warehouse) {
            product.changeQuantity(-1);
        }
        System.out.println("После changeQuantity(-1) у всех товаров:");
        ConsolePrinter.printWarehouse(warehouse);
    }

    private static void testCopy() {
        System.out.println();
        System.out.println("2. Копирование склада");
        Warehouse original = new Warehouse(3);
        original.addProduct(DemoData.create("laptop"));
        original.addProduct(DemoData.create("milk"));

        Warehouse copy = new Warehouse(original);
        for (Product product : copy) {
            product.changeQuantity(-1);
            product.setPrice(new Price(product.getPrice() * 2));
        }

        System.out.println("original:");
        ConsolePrinter.printWarehouse(original);
        System.out.println("copy (кол-во -1, цена x2):");
        ConsolePrinter.printWarehouse(copy);

        Product first = copy.iterator().next();
        System.out.println("first.getWarehouse() == copy: " + (first.getWarehouse() == copy));
    }

    private static void testTransferFrom() {
        System.out.println();
        System.out.println("3. transferFrom");
        Warehouse source = new Warehouse(4);
        Product jacket = DemoData.create("jacket");
        source.addProduct(jacket);
        source.addProduct(DemoData.create("tshirt"));

        Warehouse target = new Warehouse(10);
        Product bread = DemoData.create("bread");
        target.addProduct(bread);

        System.out.println("source:");
        ConsolePrinter.printWarehouse(source);
        System.out.println("target:");
        ConsolePrinter.printWarehouse(target);

        target.transferFrom(source);

        System.out.println("source после transferFrom:");
        ConsolePrinter.printWarehouse(source);
        System.out.println("target после transferFrom:");
        ConsolePrinter.printWarehouse(target);
        System.out.println("jacket.getWarehouse() == target: " + (jacket.getWarehouse() == target));
        System.out.println("bread.getWarehouse() == null: " + (bread.getWarehouse() == null));
        System.out.println("лимит target не изменился: " + target.getMaxSize());

        source.addProduct(DemoData.create("milk"));
        System.out.println("source после addProduct(milk):");
        ConsolePrinter.printWarehouse(source);

        Warehouse small = new Warehouse(1);
        Product phone = DemoData.create("phone");
        small.addProduct(phone);
        try {
            small.transferFrom(target);
        } catch (IllegalStateException e) {
            System.out.println("small.transferFrom(target) -> " + e.getMessage());
        }
        System.out.println("small не изменился:");
        ConsolePrinter.printWarehouse(small);
        System.out.println("target не изменился:");
        ConsolePrinter.printWarehouse(target);
        System.out.println("phone.getWarehouse() == small: " + (phone.getWarehouse() == small));
    }

    private static void testMove() {
        System.out.println();
        System.out.println("4. Перемещение склада (moveFrom)");
        Warehouse source = new Warehouse(3);
        Product laptop = DemoData.create("laptop");
        source.addProduct(laptop);
        source.addProduct(DemoData.create("bread"));
        System.out.println("source, лимит " + source.getMaxSize() + ":");
        ConsolePrinter.printWarehouse(source);

        Warehouse moved = Warehouse.moveFrom(source);

        System.out.println("moved, лимит " + moved.getMaxSize() + ":");
        ConsolePrinter.printWarehouse(moved);
        System.out.println("source после перемещения:");
        ConsolePrinter.printWarehouse(source);
        System.out.println("laptop.getWarehouse() == moved: " + (laptop.getWarehouse() == moved));
        System.out.println("тот же объект, а не копия: " + (moved.findById(laptop.getId()).orElse(null) == laptop));
    }

    private static void testSearchAndRemove() {
        System.out.println();
        System.out.println("5. Поиск и удаление");
        Warehouse warehouse = new Warehouse(10);
        Product phone = DemoData.create("phone");
        Product milk = DemoData.create("milk");
        warehouse.addProduct(phone);
        warehouse.addProduct(milk);
        warehouse.addProduct(DemoData.create("bread"));
        warehouse.addProduct(DemoData.create("tshirt"));
        ConsolePrinter.printWarehouse(warehouse);

        System.out.println("findById(" + phone.getId() + "):");
        ConsolePrinter.printProduct(warehouse.findById(phone.getId()).orElse(null));
        System.out.println("findById(999):");
        ConsolePrinter.printProduct(warehouse.findById(999).orElse(null));
        System.out.println("findByCategory(FOOD):");
        ConsolePrinter.printList(warehouse.findByCategory(Category.FOOD));
        System.out.println("findByPrice(1490):");
        ConsolePrinter.printList(warehouse.findByPrice(1490));
        System.out.println("findByPrice(50, 2000):");
        ConsolePrinter.printList(warehouse.findByPrice(50, 2000));

        System.out.println("removeById(" + milk.getId() + "): " + warehouse.removeById(milk.getId()));
        System.out.println("removeById(999): " + warehouse.removeById(999));
        warehouse.removeByIndex(0);
        try {
            warehouse.removeByIndex(100);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("removeByIndex(100) -> " + e.getMessage());
        }
        System.out.println("После удаления:");
        ConsolePrinter.printWarehouse(warehouse);
    }

    private static void testProduct() {
        System.out.println();
        System.out.println("6. Методы товара");
        Warehouse moscow = new Warehouse(5);
        Warehouse kazan = new Warehouse(1);
        Product phone = DemoData.create("phone");
        Product laptop = DemoData.create("laptop");
        moscow.addProduct(phone);
        moscow.addProduct(laptop);

        phone.transferTo(kazan);
        System.out.println("Москва:");
        ConsolePrinter.printWarehouse(moscow);
        System.out.println("Казань:");
        ConsolePrinter.printWarehouse(kazan);

        try {
            laptop.transferTo(kazan);
        } catch (IllegalStateException e) {
            System.out.println("laptop.transferTo(kazan) -> " + e.getMessage());
        }

        try {
            DemoData.create("bread").transferTo(moscow);
        } catch (IllegalStateException e) {
            System.out.println("bread.transferTo(moscow) -> " + e.getMessage());
        }

        phone.changeQuantity(-3);
        try {
            phone.changeQuantity(-1000);
        } catch (IllegalArgumentException e) {
            System.out.println("changeQuantity(-1000) -> " + e.getMessage());
        }

        phone.setPrice(new Price(69990));
        try {
            phone.setPrice(new Price(-1));
        } catch (IllegalArgumentException e) {
            System.out.println("new Price(-1) -> " + e.getMessage());
        }

        System.out.println("Казань после изменений:");
        ConsolePrinter.printWarehouse(kazan);
    }
}
