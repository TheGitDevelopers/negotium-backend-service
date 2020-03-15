package com.negotium.negotiumapp.model.warehouse;

import java.util.Map;

public class Shopping {
    private static ProductList stockList = new ProductList();

    public static void main(String[] args) {
        Product temp = new Product("Bread", 1, 2.6, 100);
        stockList.addStock(temp);

        temp = new Product("Water 1,5L bottle", 2, 1.7, 100);

        stockList.addStock(temp);

        temp = new Product("Tomato per item", 3, 1.5, 300);
        stockList.addStock(temp);

        temp = new Product("Hummus", 4, 6, 100);
        stockList.addStock(temp);

        temp = new Product("Salomon 300g", 5, 25, 20);
        stockList.addStock(temp);

        temp = new Product("Car Toy", 6, 30, 100);
        stockList.addStock(temp);

        temp = new Product("Rice 500g", 7, 2, 100);
        stockList.addStock(temp);

        temp = new Product("Avocado per item", 8, 1.7, 3);
        stockList.addStock(temp);

        temp = new Product("Rice Milk 1L", 9, 5, 100);
        stockList.addStock(temp);

        System.out.println(stockList.toString());

        for (Integer index :
                stockList.getAllItems().keySet()) {
            System.out.println(index);
        }

        Basket robertsBasket = new Basket("Robert");
        sellItem(robertsBasket, "Avocado per item", 8, 3);
        System.out.println(robertsBasket);
        sellItem(robertsBasket, "Water 1,5L bottle", 2, 12);
        System.out.println(robertsBasket);
        sellItem(robertsBasket, "Hummus", 4, 1);
        System.out.println(robertsBasket);
        sellItem(robertsBasket, "Salomon 300g", 5, 2);
        System.out.println(robertsBasket);
        sellItem(robertsBasket, "Tomato per item", 3, 10);
        System.out.println(robertsBasket);

        System.out.println(stockList);

        stockList.getAllItems().get(4).adjustStock(3000);
//        Collections.unmodifiableMap(list) provide unmodifiable Map.
//        Objects inside this Map are still modifiable.
        System.out.println("Hummus +3000: \n" + stockList);
        stockList.getItemfromStock(4).adjustStock(-2000);
        System.out.println("Hummus -2000: \n" + stockList);

//        Here bot price and list are immutable. Check priceList().
        for (Map.Entry<Integer, Double> price : stockList.priceList().entrySet()) {
            System.out.println(price.getKey() + " cost " + price.getValue());
        }

    }

    public static int sellItem(Basket basket, String itemName, int index, int quantity) {
        Product stockItem = stockList.getItemfromStock(index);

        if (stockItem == null) {
            System.out.println("We don't sell " + itemName);
            return 0;
        }
        if (stockList.sellStock(itemName, index, quantity) != 0) {
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        return 0;
    }
}
