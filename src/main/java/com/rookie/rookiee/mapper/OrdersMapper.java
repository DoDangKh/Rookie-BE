package com.rookie.rookiee.mapper;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import com.rookie.rookiee.dto.OrdersDto;
import com.rookie.rookiee.dto.Orders_ProductsDto;
import com.rookie.rookiee.entity.Orders;
import com.rookie.rookiee.entity.Orders_Products;

public class OrdersMapper {

    public static OrdersDto toDto(Orders orders) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setId(orders.getId());
        ordersDto.setIdUser(orders.getEusers().getId());

        Set<Orders_ProductsDto> orders_ProductsDtos = new HashSet<>();

        for (Orders_Products o : orders.getOrders_products()) {
            orders_ProductsDtos.add(Orders_ProductsMapper.toDto(o));
        }

        ordersDto.setOrders_ProductsDtos(orders_ProductsDtos);

        ordersDto.setStatus(orders.getStatus());

        ordersDto.setDate(dateTimeFormatter.format(orders.getDateCreated()));

        return ordersDto;
    }

}
