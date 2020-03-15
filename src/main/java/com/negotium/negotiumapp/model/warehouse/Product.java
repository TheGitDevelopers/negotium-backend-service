package com.negotium.negotiumapp.model.warehouse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product extends AbstractProduct {

    @NotNull
    @Column(name = "name_product")
    private String name;

    @NotNull
    @Column(name = "status_product")
    private ProductStatus status;

    @Column(name = "product_index")
    private int productIndex;

    public Product() {
    }

    public Product(@NotNull String name, int productIndex, double price) {
        super(price);
        this.name = name;
        this.productIndex = productIndex;
        this.status = ProductStatus.TEMPORARY;
    }

    public Product(@NotNull String name, int productIndex, double price, int quantityStock) {
        super(price, quantityStock);
        this.name = name;
        this.productIndex = productIndex;
        this.status = ProductStatus.TEMPORARY;
    }

    public Product(@NotNull String name, int productIndex, double price, int quantityStock,
                   @NotNull ProductStatus status) {
        super(price, quantityStock);
        this.name = name;
        this.productIndex = productIndex;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public BigDecimal getTotal_price() {
        return super.getTotal_price();
    }

    @Override
    public LocalDateTime getExpiryDate() {
        return super.getExpiryDate();
    }

    @Override
    public int getQuantityStock() {
        return super.getQuantityStock();
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
                "\t expiry date = " + expiryDate +
                '}';
    }
}