package com.rookie.rookiee.service;

import java.util.List;

import com.rookie.rookiee.dto.CartsDto;

public interface CartsService {

    List<CartsDto> findByUserId(Long userId);

    CartsDto addToCarts(CartsDto cartsDto);

    CartsDto updateCarts(CartsDto cartsDto, Long id);

    void DeleteCarts(Long id);

}
