package com.rookie.rookiee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rookie.rookiee.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {

}
