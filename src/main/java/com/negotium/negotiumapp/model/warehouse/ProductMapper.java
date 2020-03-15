package com.negotium.negotiumapp.model.warehouse;

public class ProductMapper {

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setStatus(product.getStatus());
        dto.setPrice(product.getPrice());
        dto.setTotal_price(product.getTotal_price());
        dto.setQuantityStock(product.getQuantityStock());
        dto.setExpiryDate(product.getExpiryDate());

        return dto;
    }

    public static Product toEntity(ProductDto product) {
        Product entity = new Product(
                product.getName(),
                product.getPrice(),
                product.getQuantityStock(),
                product.getStatus());
        entity.setId(product.getId());
        entity.setPrice(product.getPrice());
        entity.setExpiryDate(product.getExpiryDate());

        return entity;
    }
}
