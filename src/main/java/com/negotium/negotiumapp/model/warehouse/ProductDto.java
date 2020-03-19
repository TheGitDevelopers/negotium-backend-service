package com.negotium.negotiumapp.model.warehouse;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductDto {

    protected LocalDateTime expiryDate;
    private Long id;
    private String name;
    private int productIndex;
    private ProductStatus status;
    private double price;
    private Double total_price;
    private int quantityStock;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, int productIndex, ProductStatus status, double price, Double total_price, int quantityStock, LocalDateTime expiryDate) {
        this.id = id;
        this.name = name;
        this.productIndex = productIndex;
        this.status = status;
        this.price = price;
        this.total_price = total_price;
        this.quantityStock = quantityStock;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getTotal_price() {
        return total_price;
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
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDto)) return false;
        ProductDto that = (ProductDto) o;
        return Double.compare(that.price, price) == 0 &&
                id.equals(that.id) &&
                name.equals(that.name) &&
                status == that.status &&
                total_price.equals(that.total_price) &&
                expiryDate.equals(that.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, price, total_price, expiryDate);
    }
}
