package com.negotium.negotiumapp.model.warehouse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@MappedSuperclass
public abstract class AbstractProduct implements Comparable<Product> {

    @NotNull
    @Column(name = "expiry_date")
    protected LocalDateTime expiryDate;
    @Column(name = "price_product")
    protected double price;
    @NotNull
    @Column(name = "totalprice")
    protected Double total_price;
    @Column(name = "quantity_product")
    protected int quantityStock;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    public AbstractProduct() {
    }

    public AbstractProduct(@NotNull double price) {
        this.price = price;
        this.quantityStock = 0;
        this.total_price = getTotal_price();
        this.expiryDate = LocalDateTime.now().plus(30, ChronoUnit.DAYS);
    }

    public AbstractProduct(@NotNull double price, @NotNull int quantityStock) {
        this.price = price;
        this.quantityStock = quantityStock;
        this.total_price = getTotal_price();
        this.expiryDate = LocalDateTime.now().plus(30, ChronoUnit.DAYS);
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

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getTotal_price() {
        return calcTotalPrice();
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }

    public LocalDateTime getExpiryDate() {
        if (isFresh()) {
            return expiryDate;
        } else {
            throw new IllegalArgumentException("This product is out-of-date.");
        }
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        if (isFresh(expiryDate)) {
            this.expiryDate = expiryDate;
        } else {
            throw new IllegalArgumentException("Cannot set already expired date. Please search for fresh products.");
        }
    }

    private Double calcTotalPrice() {
        if (quantityStock == 0) {
            return Double.NaN;
        }
        return ((double) this.quantityStock) * this.price;
    }

    private boolean isFresh() {
        return !(this.expiryDate.isBefore(LocalDateTime.now()));
    }

    private boolean isFresh(LocalDateTime newExpiryDate) {
        return !(newExpiryDate.isBefore(LocalDateTime.now()));
    }
}