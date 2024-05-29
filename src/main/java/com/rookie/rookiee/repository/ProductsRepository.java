package com.rookie.rookiee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rookie.rookiee.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    // @EntityGraph(attributePaths = "categity")
    // Optional<Products> findOneById(Long id);

}
