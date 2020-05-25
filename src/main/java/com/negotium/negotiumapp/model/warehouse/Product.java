package com.negotium.negotiumapp.model.warehouse;

import com.negotium.negotiumapp.security.config.date.NegotiumDateTimeConfig;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product extends AbstractProduct {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "status_prod")
    private ProductStatus status;

    @Column(name = "index_prod")
    private Integer productIndex;

    public Product() {
        super();
    }

    public Product(@NotNull String name, Integer productIndex, double price) {
        super(price);
        this.name = name;
        this.productIndex = productIndex;
        this.status = ProductStatus.TEMPORARY;
    }

    public Product(@NotNull String name, Integer productIndex, double price,
                   int quantityStock) {
        super(price, quantityStock);
        this.name = name;
        this.productIndex = productIndex;
        this.status = ProductStatus.TEMPORARY;
    }

    public Product(@NotNull String name, Integer productIndex, double price,
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

    public LocalDate getExpiryDate() {
        return super.getExpiryDate();
    }

    public void setExpiryDate(LocalDate expiryDate) {
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

    public Integer getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(Integer productIndex) {
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
                "index = " + this.productIndex +
                "\t name = " + this.name +
                "\t status = " + this.status +
                "\t price = " + super.getPrice() +
                "\t expiry date = " + expiryDate.format(DateTimeFormatter.ofPattern(NegotiumDateTimeConfig.dateFormat)) +
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