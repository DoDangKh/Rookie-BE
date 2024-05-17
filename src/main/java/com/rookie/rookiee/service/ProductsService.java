package com.rookie.rookiee.service;

import com.rookie.rookiee.dto.ProductsDto;

public interface ProductsService {

    ProductsDto findById(Long id);

    ProductsDto save(ProductsDto productsDto);

    void deleteById(Long id);

    ProductsDto update(ProductsDto productsDto, Long id);
}
