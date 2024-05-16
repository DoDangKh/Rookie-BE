package com.rookie.rookiee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rookie.rookiee.entity.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

}
