package com.negotium.negotiumapp.model.warehouse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "product")
@SecondaryTable(name = "parent_product")
public class Product extends AbstractProduct {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "status")
    private ProductStatus status;

    @Column(name = "index")
    private Integer productIndex;

    public Product() {
        super();
    }

    public Product(@NotNull String name, int productIndex, double price) {
        super(price);
        this.name = name;
        this.productIndex = productIndex;
        this.status = ProductStatus.TEMPORARY;
    }

    public Product(@NotNull String name, int productIndex, double price,
                   int quantityStock) {
        super(price, quantityStock);
        this.name = name;
        this.productIndex = productIndex;
        this.status = ProductStatus.TEMPORARY;
    }

    public Product(@NotNull String name, int productIndex, double price,
                   int quantityStock, @NotNull ProductStatus status) {
        super(price, quantityStock);
        this.name = name;
        this.productIndex = productIndex;
        this.status = status;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public double getPrice() {
        return super.getPrice();
    }

    public void setPrice(double price) {
        super.setPrice(price);
    }

    public int getQuantityStock() {
        return super.getQuantityStock();
    }

    public Double getTotal_price() {
        return super.getTotal_price();
    }

    public void setTotal_price(Double total_price) {
        super.setTotal_price(total_price);
    }

    public LocalDateTime getExpiryDate() {
        return super.getExpiryDate();
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        super.setExpiryDate(expiryDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
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
        return "Product{" +
                "id = " + super.getId() +
                "\t name = " + this.name +
                "\t status = " + this.status +
                "\t price = " + super.getPrice() +
                "\t expiry date = " + expiryDate.format(DateTimeFormatter.ISO_DATE) +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        if (this == o) {
            return 0;
        }
        if (o != null) {
            return this.productIndex.compareTo(o.getProductIndex());
        }
        throw new NullPointerException();
    }
}