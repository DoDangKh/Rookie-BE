package com.rookie.rookiee.service.implemantion;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.rookie.rookiee.entity.Categories;
import com.rookie.rookiee.entity.Products;

import jakarta.persistence.criteria.JoinType;

public class ProductsSpecification {

    public static Specification<Products> hasName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Products> hasCategory(List<Categories> categories) {

        return (root, query, criteriaBuilder) -> {
            if (categories == null || categories.isEmpty()) {
                // If categories is null or empty, return a condition that always evaluates to
                // true
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                return root.join("categories", JoinType.INNER).in(categories);
            }
        };
    }

}
