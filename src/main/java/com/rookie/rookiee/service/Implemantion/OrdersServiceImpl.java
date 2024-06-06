package com.rookie.rookiee.service.implemantion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rookie.rookiee.dto.OrdersDto;
import com.rookie.rookiee.dto.Orders_ProductsDto;
import com.rookie.rookiee.entity.Eusers;
import com.rookie.rookiee.entity.Orders;
import com.rookie.rookiee.entity.Orders_Products;
import com.rookie.rookiee.entity.Products;
import com.rookie.rookiee.exception.AppException;
import com.rookie.rookiee.mapper.OrdersMapper;
import com.rookie.rookiee.repository.EusersRepository;
import com.rookie.rookiee.repository.OrdersRepository;
import com.rookie.rookiee.repository.ProductsRepository;
import com.rookie.rookiee.service.OrdersService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    private final ProductsRepository productsRepository;

    private final EusersRepository eusersRepository;

    @Override
    @Transactional
    public OrdersDto createOrders(OrdersDto ordersDto) {

        Orders orders = new Orders();

        orders.setStatus(ordersDto.getStatus());

        Eusers eusers = eusersRepository.findById(ordersDto.getIdUser())
                .orElseThrow(() -> new AppException("Eusers not Found", HttpStatus.NOT_FOUND));

        Set<Orders_Products> setOP_orders = new HashSet<>();

        for (Orders_ProductsDto o : ordersDto.getOrders_ProductsDtos()) {

            Products products = productsRepository.findById(o.getProductsDto().getId())
                    .orElseThrow(() -> new AppException("Products Not Found", HttpStatus.NOT_FOUND));

            Orders_Products orders_Products = new Orders_Products();

            orders_Products.setAmounts(o.getAmount());

            orders_Products.setProducts(products);

            orders_Products.setOrders(orders);

            Set<Orders_Products> setOP = products.getOrders_products();

            if (setOP == null)
                setOP = new HashSet<>();

            setOP.add(orders_Products);

            products.setOrders_products(setOP);

            productsRepository.save(products);

            setOP_orders.add(orders_Products);

        }

        orders.setEusers(eusers);

        orders.setOrders_products(setOP_orders);

        Set<Orders> eusersOrders = eusers.getOrders();

        eusersOrders.add(orders);

        eusersRepository.save(eusers);

        return OrdersMapper.toDto(ordersRepository.save(orders));

    }

    @Override
    @Transactional
    public List<OrdersDto> getOrdersByUserId(Long userId) {

        Eusers eusers = eusersRepository.findById(userId)
                .orElseThrow(() -> new AppException("Users Not Found", HttpStatus.NOT_FOUND));

        List<Orders> orders = ordersRepository.findByEusers(eusers);

        List<OrdersDto> ordersDtos = new ArrayList<>();

        for (Orders o : orders) {
            ordersDtos.add(OrdersMapper.toDto(o));
        }

        return ordersDtos;

    }

}
