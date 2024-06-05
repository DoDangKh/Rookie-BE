package com.rookie.rookiee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rookie.rookiee.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
