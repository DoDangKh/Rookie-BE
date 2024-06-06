package com.rookie.rookiee.service;

import java.util.List;

import com.rookie.rookiee.dto.OrdersDto;

public interface OrdersService {

    OrdersDto createOrders(OrdersDto ordersDto);

    List<OrdersDto> getOrdersByUserId(Long userId);

}
