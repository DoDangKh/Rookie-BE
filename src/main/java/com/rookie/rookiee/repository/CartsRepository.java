package com.rookie.rookiee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rookie.rookiee.entity.Carts;
import com.rookie.rookiee.entity.Eusers;
import com.rookie.rookiee.entity.Products;

import java.util.List;
import java.util.Optional;

public interface CartsRepository extends JpaRepository<Carts, Long> {

    List<Carts> findByEusers(Eusers eusers);

    Carts findByProductsAndEusers(Products products, Eusers eusers);

}
