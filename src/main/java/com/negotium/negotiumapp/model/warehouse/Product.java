package com.negotium.negotiumapp.model.warehouse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product implements Comparable<Product> {

    @NotNull
    @Column(name = "expiry_date")
    protected LocalDateTime expiryDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "price")
    private double price;
    @Column(name = "quantity")
    private int quantityStock;
    @NotNull
    @Column(name = "totalprice")
    private Double total_price;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "status")
    private ProductStatus status;
    @Column(name = "index")
    private Integer productIndex;

    public Product() {
    }

    public Product(@NotNull String name, int productIndex, double price) {
        this.price = price;
        this.quantityStock = 0;
        this.total_price = getTotal_price();
        this.name = name;
        this.productIndex = productIndex;
        this.status = ProductStatus.TEMPORARY;
    }

    public Product(@NotNull String name, int productIndex, double price,
                   int quantityStock) {
        this.price = price;
        this.quantityStock = quantityStock;
        this.total_price = getTotal_price();
        this.name = name;
        this.productIndex = productIndex;
        this.status = ProductStatus.TEMPORARY;
    }

    public Product(@NotNull String name, int productIndex, double price,
                   int quantityStock, @NotNull ProductStatus status) {
        this.price = price;
        this.quantityStock = quantityStock;
        this.total_price = getTotal_price();
        this.name = name;
        this.productIndex = productIndex;
        this.status = status;
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

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }

    public Double getTotal_price() {
        return calcTotalPrice();
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
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

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
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
                "id = " + this.id +
                "\t name = " + this.name +
                "\t status = " + this.status +
                "\t price = " + this.price +
                "\t expiry date = " + this.expiryDate +
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

    private Double calcTotalPrice() {
        if (quantityStock == 0) {
            return Double.NaN;
        }
        double multiply = ((double) this.quantityStock) * this.price;
        return multiply;
    }
}