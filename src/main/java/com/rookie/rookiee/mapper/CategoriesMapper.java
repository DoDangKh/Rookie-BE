package com.rookie.rookiee.mapper;

import com.rookie.rookiee.dto.CategoriesDto;
import com.rookie.rookiee.entity.Categories;

public class CategoriesMapper {

    public static CategoriesDto toCategoriesDto(Categories categories) {
        return new CategoriesDto(categories.getId(), categories.getName(), categories.getDescription(),
                categories.getStatus());
    }

    public static Categories categoriesDtoToCategories(CategoriesDto categoriesDto) {
        Categories categories = new Categories();
        categories.setName(categoriesDto.getName());
        categories.setDescription(categoriesDto.getDescription());
        categories.setId(categoriesDto.getId());
        categories.setStatus(categoriesDto.isStatus());
        return categories;
    }

}
