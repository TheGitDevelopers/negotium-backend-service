package com.negotium.negotiumapp.controller;

import com.negotium.negotiumapp.model.warehouse.ProductDto;
import com.negotium.negotiumapp.model.warehouse.ProductStatus;
import com.negotium.negotiumapp.security.SecurityConstans;
import com.negotium.negotiumapp.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void findAll() throws Exception {
//        given
        LocalDateTime expiry_date = LocalDateTime.now().plus(5, ChronoUnit.DAYS);
        ProductDto dto1 = new ProductDto(
                33L,
                "Avocado per item",
                8,
                ProductStatus.SPECIAL,
                1.7,
                5.1,
                3,
                expiry_date);
        ProductDto dto2 = new ProductDto(32L, "Rice Milk 1L", 7, ProductStatus.CONSTANT, 7, 5.1, 42, expiry_date);

//        when
        given(service.findAll()).willReturn(Arrays.asList(dto1, dto2));

//        then
        mockMvc.perform(
                get(SecurityConstans.API_PRODUCTS + "/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products", hasSize(2)));
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }
}