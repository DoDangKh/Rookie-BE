package com.rookie.rookiee.mapper;

import org.springframework.data.domain.Page;

import com.rookie.rookiee.dto.PageProductDto;
import com.rookie.rookiee.dto.ProductsDto;
import com.rookie.rookiee.entity.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductPageMapper {

    public static PageProductDto pageProductDto(Page<Products> pageProduct) {

        PageProductDto pageProductDto = new PageProductDto();

        pageProductDto.setNumber(pageProduct.getNumber());

        List<ProductsDto> productsDtos = new ArrayList<>();

        for (Products i : pageProduct.getContent()) {

            ProductsDto temp = ProductsMapper.maptoProductsDto(i);
            productsDtos.add(temp);
        }

        pageProductDto.setProductdtos(productsDtos);

        pageProductDto.setSize(pageProduct.getSize());

        pageProductDto.setPages(pageProduct.getTotalPages());

        return pageProductDto;
    }

}
