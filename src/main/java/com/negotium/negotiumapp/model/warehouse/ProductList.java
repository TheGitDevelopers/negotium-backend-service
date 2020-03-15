package com.negotium.negotiumapp.model.warehouse;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductList {
    private final Map<Integer, Product> list;

    public ProductList() {
        this.list = new LinkedHashMap<>();
    }
}
