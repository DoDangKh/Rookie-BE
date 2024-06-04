package com.rookie.rookiee.service.implemantion;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.rookie.rookiee.entity.Categories;
import com.rookie.rookiee.entity.Products;

import io.swagger.v3.oas.models.SpecVersion;
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

    public static Specification<Products> hasPriceAbove(Double price) {
        return (root, query, criteriaBuilder) -> {
            if (price == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
        };
    }

    public static Specification<Products> hasPriceBelow(Double price) {
        return (root, query, criteriaBuilder) -> {
            if (price == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }

            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
        };
    }

    public static Specification<Products> isFeature(Boolean feature) {

        return (root, query, criteriaBuilder) -> {
            if (feature == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.equal(root.get("feature"), feature);
        };
    }

    public static Specification<Products> isActive(Boolean isActive) {

        return (root, query, criteriaBuilder) -> {
            if (isActive == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.equal(root.get("isActive"), isActive);
        };
    }

}
