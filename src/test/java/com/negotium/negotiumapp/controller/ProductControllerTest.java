package com.negotium.negotiumapp.controller;

import com.negotium.negotiumapp.model.warehouse.Product;
import com.negotium.negotiumapp.model.warehouse.ProductDto;
import com.negotium.negotiumapp.model.warehouse.ProductMapper;
import com.negotium.negotiumapp.model.warehouse.ProductStatus;
import com.negotium.negotiumapp.security.SecurityConstans;
import com.negotium.negotiumapp.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest extends AbstractRestControllerTest {

    @Mock
    ProductService service;

    @InjectMocks
    ProductController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void should_find_all_products() throws Exception {
//        given
        LocalDate expiry_date = LocalDate.now().plus(5, ChronoUnit.DAYS);
        ProductDto dto1 = new ProductDto(
                33L,
                "Avocado per item",
                8,
                ProductStatus.SPECIAL,
                1.7,
                5.1,
                3,
                expiry_date);
        ProductDto dto2 = new ProductDto(
                32L,
                "Rice Milk 1L",
                7,
                ProductStatus.CONSTANT,
                7,
                5.1,
                42,
                expiry_date);

//        when
        given(service.findAll()).willReturn(Arrays.asList(dto1, dto2));

//        then
        mockMvc.perform(
                get(SecurityConstans.API_PRODUCTS + "/findall")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Avocado per item")))
                .andExpect(jsonPath("$[1].name", is("Rice Milk 1L")));
    }

    @Test
    void should_find_by_id() throws Exception {
        LocalDate expiry_date = LocalDate.now().plus(3, ChronoUnit.DAYS);

//        given
        ProductDto dto = new ProductDto(
                33L,
                "Avocado per item",
                8,
                ProductStatus.SPECIAL,
                1.7,
                5.1,
                3,
                expiry_date);

        given(service.findById(anyLong())).willReturn(Optional.of(dto));

//        when
//        then
        mockMvc.perform(
                get(SecurityConstans.API_PRODUCTS + "/33")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", equalTo(1.7)))
                .andExpect(jsonPath("$.productIndex", equalTo(8)));
    }

    @Test
    void should_save_rice_milk() throws Exception {
//        given
        LocalDate expiry_date = LocalDate.now().plus(20, ChronoUnit.DAYS);
        ProductDto dto = new ProductDto(
                null,
                "Rice Milk 1L",
                7,
                ProductStatus.CONSTANT,
                7,
                5.1,
                42,
                expiry_date);

        given(service.addProduct(any(ProductDto.class))).willReturn(dto);

//        when
//        then
        mockMvc.perform(
                post(SecurityConstans.API_PRODUCTS + "/save")
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Rice Milk 1L")))
                .andExpect(jsonPath("$.status", equalTo(ProductStatus.CONSTANT.toString())))
                .andExpect(jsonPath("$.productIndex", equalTo(7)));

    }

    @Test
    void should_update_product() throws Exception {
//        given
        LocalDate expiry_date = LocalDate.now().plus(3, ChronoUnit.DAYS);
        ProductDto avocado = new ProductDto(
                (long) 33,
                "Avocado per item",
                8,
                ProductStatus.SPECIAL,
                1.7,
                5.1,
                3,
                expiry_date);

        Product entity = ProductMapper.toEntity(avocado);
        entity.setQuantityStock(297);
        entity.setPrice(1.5);
        Double total_price = entity.getTotal_price();
        entity.setTotal_price(total_price);
        ProductDto updatedDTO = ProductMapper.toDto(entity);

        given(service.updateProduct(any(ProductDto.class))).willReturn(updatedDTO);

        mockMvc.perform(
                put(SecurityConstans.API_PRODUCTS + "/33", 33L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", equalTo(1.5)))
                .andExpect(jsonPath("$.price", not(equalTo(1.7))))
                .andExpect(jsonPath("$.productIndex", equalTo(8)))
                .andExpect(jsonPath("$.quantityStock", equalTo(297)))
                .andExpect(jsonPath("$.total_price", equalTo(445.5)))
                .andExpect(jsonPath("$.total_price", not(equalTo(2400))));

    }
}