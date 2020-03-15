package com.negotium.negotiumapp.model.warehouse;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProductList {
    private final Map<String, AbstractProduct> list;

    public ProductList() {
        this.list = new LinkedHashMap<>();
    }
}
