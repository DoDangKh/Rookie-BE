package com.rookie.rookiee.mapper;

import com.rookie.rookiee.dto.Orders_ProductsDto;
import com.rookie.rookiee.entity.Orders_Products;

public class Orders_ProductsMapper {

    public static Orders_ProductsDto toDto(Orders_Products orders_Products) {
        Orders_ProductsDto orders_ProductsDto = new Orders_ProductsDto();
        orders_ProductsDto.setAmount(orders_Products.getAmounts());

        orders_ProductsDto.setId(orders_Products.getId());

        orders_ProductsDto.setProductsDto(ProductsMapper.maptoProductsDto(orders_Products.getProducts()));

        return orders_ProductsDto;

    }

    public static Orders_Products toEntity(Orders_ProductsDto orders_ProductsDto, Orders_Products orders_Products) {

        orders_Products.setAmounts(orders_ProductsDto.getAmount());

        orders_Products.setId(orders_ProductsDto.getId());

        return orders_Products;

    }

}
