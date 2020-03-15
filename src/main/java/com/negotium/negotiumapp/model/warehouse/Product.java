package com.negotium.negotiumapp.model.warehouse;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Product extends AbstractProduct {

    @NotNull
    @Column(name = "name_product")
    private final String name;

    @NotNull
    @Column(name = "status_product")
    private final ProductStatus status;

    public Product(@NotNull String name, double price) {
        super(price);
        this.name = name;
        this.status = ProductStatus.TEMPORARY;
    }

    public Product(@NotNull String name, double price, int quantityStock) {
        super(price, quantityStock);
        this.name = name;
        this.status = ProductStatus.TEMPORARY;
    }

    public Product(@NotNull String name, double price, int quantityStock,
                   @NotNull ProductStatus status) {
        super(price, quantityStock);
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public ProductStatus getStatus() {
        return status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return name.equals(product.name) &&
                status == product.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status) + 31;
    }

    @Override
    public String toString() {
        return "ConstantProduct{" +
                "id = " + super.getId() +
                "\t name = " + this.name +
                "\t status = " + this.status +
                "\t price = " + super.getPrice() +
                "\t expiry date = " + expiryDate.format(DateTimeFormatter.BASIC_ISO_DATE) +
                '}';
    }
}
