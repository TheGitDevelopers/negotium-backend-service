package com.negotium.negotiumapp.model.warehouse;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductList {
    private final Map<Integer, Product> list;

    public ProductList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(Product item) {
        if (item != null) {
            // check if already have quantities of this item
            Product inStock = list.getOrDefault(item.getProductIndex(), item);
            // If there are already stock on this item, adjust the quantity
            if (inStock != item) {
                item.adjustStock(inStock.getQuantityStock());
            }

            list.put(item.getProductIndex(), item);
            return item.getQuantityStock();
        }
        return 0;
    }

    public int sellStock(String item, Integer index, int quantity) {
        Product inStock = list.getOrDefault(index, null);

        if (((inStock != null) && (inStock.getQuantityStock()) >= quantity) && (quantity >= 0)) {
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    // to jest typowy getter
    public Product getItemfromStock(int key) {
        return list.get(key);
    }

    public Map<Integer, Product> getAllItems() {
        return Collections.unmodifiableMap(list);
    }

    public Map<Integer, Double> priceList() {
        Map<Integer, Double> prices = new LinkedHashMap<>();

        for (Map.Entry<Integer, Product> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nStock List:\n");
        double totalCost = 0.0;
        for (Map.Entry<Integer, Product> item :
                list.entrySet()) {
            Product stockItem = item.getValue();
            double itemValue = stockItem.getPrice() * stockItem.getQuantityStock();
            s.append(stockItem).append(". There are ").append(stockItem.getQuantityStock()).append(" in stock. ").append("\nValue of items: ");
            s.append(String.format("%.2f", itemValue)).append("\n");
            totalCost += itemValue;
        }
        return s + "Total stock value: " + String.format("%.2f", totalCost);
    }
}