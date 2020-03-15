package com.negotium.negotiumapp.model.warehouse;

enum ProductStatus {
    CONSTANT("Constant"), SPECIAL("Special"), TEMPORARY("Temporary");

    private String status;

    ProductStatus(String status) {
        this.status = status;
    }
}
