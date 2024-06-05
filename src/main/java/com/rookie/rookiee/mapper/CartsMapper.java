package com.rookie.rookiee.mapper;

import com.rookie.rookiee.dto.CartsDto;
import com.rookie.rookiee.entity.Carts;
import com.rookie.rookiee.entity.Products;

public class CartsMapper {

    public static CartsDto mapToCartsDto(Carts carts) {
        CartsDto cartsDto = new CartsDto();
        cartsDto.setId(carts.getId());
        cartsDto.setAmount(carts.getAmount());
        cartsDto.setIdUser(carts.getEusers().getId());
        cartsDto.setProductsDto(ProductsMapper.maptoProductsDto(carts.getProducts()));
        return cartsDto;
    }

    public static Carts mapToCarts(CartsDto cartsDto, Carts carts) {
        carts.setAmount(cartsDto.getAmount());
        return carts;
    }

}
