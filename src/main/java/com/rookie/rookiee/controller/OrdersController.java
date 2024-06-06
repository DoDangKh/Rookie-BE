package com.rookie.rookiee.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rookie.rookiee.dto.OrdersDto;
import com.rookie.rookiee.service.OrdersService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping("/add")
    public ResponseEntity<OrdersDto> postMethodName(@RequestBody OrdersDto ordersDto) {
        System.out.println(ordersDto);

        OrdersDto saved = ordersService.createOrders(ordersDto);

        return ResponseEntity.created(URI.create("/api/v1/product/" + saved.getId())).body(saved);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<OrdersDto>> getMethodName(@PathVariable String id) {

        return ResponseEntity.ok().body(ordersService.getOrdersByUserId(Long.parseLong(id)));

    }

}
