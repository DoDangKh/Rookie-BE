package com.rookie.rookiee.service;

import com.rookie.rookiee.dto.AddProductDto;
import com.rookie.rookiee.dto.PageProductDto;
import com.rookie.rookiee.dto.ProductsDto;
import com.rookie.rookiee.entity.Categories;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface ProductsService {

    ProductsDto findById(Long id);

    ProductsDto save(AddProductDto productsDto);

    void deleteById(Long id);

    ProductsDto update(AddProductDto productsDto, Long id);

    List<ProductsDto> findAll();

    void deleteManyById(List<Long> idList);

    PageProductDto findProduct(String name, List<Long> categoryIds, Pageable pageable);
}
