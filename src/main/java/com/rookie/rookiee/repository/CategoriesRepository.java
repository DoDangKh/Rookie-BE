package com.rookie.rookiee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rookie.rookiee.entity.Categories;
import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    List<Categories> findByStatus(Boolean status);

}
