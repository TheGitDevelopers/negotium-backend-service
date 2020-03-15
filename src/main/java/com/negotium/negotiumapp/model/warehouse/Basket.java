package com.negotium.negotiumapp.model.warehouse;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<Product, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

    public int addToBasket(Product item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    public Map<Product, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\n>>>>\tShopping basket " + name + " contains " + list.size() +
                ((list.size() != 1) ? " items." : " item.") + "\n");
        double totalCost = 0.0;
        for (Map.Entry<Product, Integer> item : list.entrySet()) {
            s.append(item.getKey()).append(". ").append(item.getValue()).append(" purchased\n");
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "\nTotal cost " + totalCost + "\t<<<<";
    }

}
