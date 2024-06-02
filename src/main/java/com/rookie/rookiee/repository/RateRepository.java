package com.rookie.rookiee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rookie.rookiee.entity.Eusers;
import com.rookie.rookiee.entity.Products;
import com.rookie.rookiee.entity.Rates;

public interface RateRepository extends JpaRepository<Rates, Long> {

    Rates findOneByEusersAndProducts(Eusers eusers, Products products);

}