package com.negotium.negotiumapp.model.warehouse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractProduct implements Comparable<AbstractProduct> {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_product")
    private Long id;

    @Column(name = "price_product")
    private double price;

    @NotNull
    @Column(name = "totalprice_product")
    private BigDecimal total_price;

    @Column(name = "quantity_product")
    private int quantityStock;

    @NotNull
    @Column(name = "expiry_date")
    protected LocalDateTime expiryDate;

    public AbstractProduct() {
    }

    public AbstractProduct(@NotNull double price) {
        this.price = price;
        this.quantityStock = 0;
        this.total_price = BigDecimal.ZERO;
    }

    public AbstractProduct(@NotNull double price, @NotNull int quantityStock) {
        this.price = price;
        this.quantityStock = quantityStock;
        this.total_price = BigDecimal.valueOf(quantityStock * price);
    }

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public boolean adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if (newQuantity > 0) {
            this.quantityStock = newQuantity;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public abstract String toString();

    @Override
    public int compareTo(AbstractProduct o) {
        if (this == o) {
            return 0;
        }
        if (o != null) {
            return this.id.compareTo(o.getId());
        }
        throw new NullPointerException();
    }
}