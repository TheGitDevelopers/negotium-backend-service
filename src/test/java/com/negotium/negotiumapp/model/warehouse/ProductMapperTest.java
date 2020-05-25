package com.negotium.negotiumapp.model.warehouse;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {

    @Test
    void toDto() {
//        given
        Product product = new Product("Avocado per item", 8, 1.7, 3);

//        when
        ProductDto dto = ProductMapper.toDto(product);

//        then
        assertEquals(product.getName(), dto.getName());
        assertEquals(product.getPrice(), dto.getPrice());
        assertEquals(product.getExpiryDate(), dto.getExpiryDate());
    }

    @Test
    void toEntity() {
        LocalDate expirydate = LocalDate.now().plus(5, ChronoUnit.DAYS);
        ProductDto productDto = new ProductDto(33L, "Avocado per item", 8, ProductStatus.SPECIAL, 1.7, 5.1, 3, expirydate);

//        when
        Product entity = ProductMapper.toEntity(productDto);

//        then
        assertEquals(productDto.getName(), entity.getName());
        assertEquals(productDto.getId(), entity.getId());
        assertEquals(productDto.getPrice(), entity.getPrice());
        assertEquals(productDto.getExpiryDate(), entity.getExpiryDate());
        assertEquals(productDto.getStatus(), entity.getStatus());
        assertEquals(productDto.getTotal_price(), entity.getTotal_price());
    }
}