package com.rookie.rookiee.service;

import static org.mockito.ArgumentMatchers.nullable;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rookie.rookiee.dto.ProductsDto;
import com.rookie.rookiee.entity.Products;
import com.rookie.rookiee.repository.ProductsRepository;
import com.rookie.rookiee.service.implemantion.ProductsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTest {

    @Mock
    ProductsRepository productsRepository;

    @InjectMocks
    ProductsServiceImpl productsServiceImpl;

    Products products;

    ProductsDto productsDto;

    @BeforeEach
    void setUp() {
        products.builder()
                .amount(Long.parseLong("10"))
                .carts(null)
                .categories(null)
                .description("null")
                .feature(true)
                .id(Long.parseLong("1"))
                .images(null)
                .isActive(true)
                .name("test")
                .orders_products(null)
                .price(100.00)
                .rates(null);
    }

    @Test
    void ProductsService_findById_Exception() {

    }

}
