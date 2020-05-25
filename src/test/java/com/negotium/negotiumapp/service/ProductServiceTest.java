package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.model.warehouse.Product;
import com.negotium.negotiumapp.model.warehouse.ProductDto;
import com.negotium.negotiumapp.model.warehouse.ProductMapper;
import com.negotium.negotiumapp.repository.ProductRepository;
import com.negotium.negotiumapp.security.config.date.NegotiumDateTimeConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

class ProductServiceTest {

    ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    void should_find_all_products() {
        //given
        List<Product> products = getProducts();
        given(productRepository.findAll()).willReturn(products);

        //when
        List<ProductDto> productDtos = productService.findAll();
        //then
        assertEquals(9, productDtos.size());
        assertEquals(products.get(2).getProductIndex(),
                productDtos.get(2).getProductIndex());
    }

    @Test
    void should_find_all_by_tomato_name_containing() {
//        given
        String tomato = "Tomato";
        List<Product> products = getProducts()
                .stream()
                .filter(product -> product
                        .getName()
                        .contains(tomato))
                .collect(Collectors.toList());
        given(productRepository.findAllByNameContaining(anyString())).willReturn(products);

//        when
        List<ProductDto> productDTOs = productService.findAllByNameContaining(tomato);

//        System.out.println(productDTOs.stream().map(ProductMapper::toEntity)
//                .collect(Collectors.toList()).toString());

//        then
        assertEquals(1, productDTOs.size());
    }

    @Test
    void should_find_by_id() {
//        given
        Product foundProduct = getTestProduct("Water 1,5L bottle", 2, 1.7, 100);
        foundProduct.setId(111L);
        given(productRepository.findById(anyLong())).willReturn(Optional.of(foundProduct));

//        when
        Optional<ProductDto> optionalProductDto = productService.findById(foundProduct.getId());
//        then
        assertEquals(foundProduct.getId(), optionalProductDto.get().getId());
    }

    @Test
    void should_add_product() {
//        given
        Product entity = getTestProduct("Salomon 300g", 5, 25, 20);
        entity.setId(33L);
        given(productRepository.findByProductIndex(anyInt()))
                .willReturn(Optional.of(entity));
        given(productRepository.save(any(Product.class)))
                .willReturn(entity);

//        when
        ProductDto saveDTO = productService.addProduct(ProductMapper.toDto(entity));

//        then
        assertEquals(entity.getId(), saveDTO.getId());
        assertEquals(entity.getName(), saveDTO.getName());
        assertEquals(entity.getProductIndex(), saveDTO.getProductIndex());
    }

    @Test
    void should_updateProduct() {
//        given
        Product oldProduct = getTestProduct("Car Toy", 6, 30, 100);
        oldProduct.setId(33L);
        String oldExipiryDate = oldProduct.getExpiryDate().format(DateTimeFormatter.ofPattern(NegotiumDateTimeConfig.dateFormat));

        Product updatedProduct = getTestProduct("Car Toy2", 6, 30, 100);
        updatedProduct.setId(33L);
        LocalDate newDate = LocalDate.now().plus(5, ChronoUnit.YEARS);
        String newDateString = newDate.format(DateTimeFormatter.ofPattern(NegotiumDateTimeConfig.dateFormat));
        updatedProduct.setExpiryDate(newDate);

        given(productRepository.findById(anyLong()))
                .willReturn(Optional.of(oldProduct));
        given(productRepository.save(any(Product.class))).willReturn(oldProduct);

//        when
        given(productRepository.save(any(Product.class)))
                .willReturn(updatedProduct);
        ProductDto updateDTO =
                productService.updateProduct(ProductMapper.toDto(updatedProduct));

//        then
        assertNotNull(updatedProduct);
        assertEquals(oldProduct.getProductIndex(), updatedProduct.getProductIndex());
        assertThat(updatedProduct.getName(), not(equalTo(oldProduct.getName())));
        assertEquals(newDate, updatedProduct.getExpiryDate());
        assertNotEquals(newDateString, oldExipiryDate);

    }

    private Product getTestProduct(String name, Integer productIndex, double price, int quantityStock) {
        return new Product(name, productIndex, price, quantityStock);
    }

    private List<Product> getProducts() {
        List<Product> stockList = new ArrayList<>();

        Product temp = new Product("Bread", 1, 2.6, 100);
        stockList.add(temp);

        temp = new Product("Water 1,5L bottle", 2, 1.7, 100);
        stockList.add(temp);

        temp = new Product("Tomato per item", 3, 1.5, 300);
        stockList.add(temp);

        temp = new Product("Hummus", 4, 6, 100);
        stockList.add(temp);

        temp = new Product("Salomon 300g", 5, 25, 20);
        stockList.add(temp);

        temp = new Product("Car Toy", 6, 30, 100);
        stockList.add(temp);

        temp = new Product("Rice 500g", 7, 2, 100);
        stockList.add(temp);

        temp = new Product("Avocado per item", 8, 1.7, 3);
        stockList.add(temp);

        temp = new Product("Rice Milk 1L", 9, 5, 100);
        stockList.add(temp);

        return stockList;
    }
}