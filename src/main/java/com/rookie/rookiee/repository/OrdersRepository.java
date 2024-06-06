package com.rookie.rookiee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rookie.rookiee.entity.Eusers;
import com.rookie.rookiee.entity.Orders;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByEusers(Eusers eusers);
}
