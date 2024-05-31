package com.rookie.rookiee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rookie.rookiee.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Long>, JpaSpecificationExecutor<Products> {

    // @EntityGraph(attributePaths = "categity")
    // Optional<Products> findOneById(Long id);

}
