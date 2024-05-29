package com.rookie.rookiee.service;

import com.rookie.rookiee.dto.AddProductDto;
import com.rookie.rookiee.dto.ProductsDto;

import java.util.List;

public interface ProductsService {

    ProductsDto findById(Long id);

    ProductsDto save(AddProductDto productsDto);

    void deleteById(Long id);

    ProductsDto update(AddProductDto productsDto, Long id);

    List<ProductsDto> findAll();

    void deleteManyById(List<Long> idList);
}
