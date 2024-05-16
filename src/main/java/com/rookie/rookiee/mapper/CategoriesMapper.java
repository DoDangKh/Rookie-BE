package com.rookie.rookiee.mapper;

import com.rookie.rookiee.dto.CategoriesDto;
import com.rookie.rookiee.entity.Categories;

public class CategoriesMapper {

    public static CategoriesDto toCategoriesDto(Categories categories) {
        return new CategoriesDto(categories.getId(), categories.getName(), categories.getDescription());
    }

    public static Categories categoriesDtoToCategories(CategoriesDto categoriesDto) {
        Categories categories = new Categories();
        categories.setName(categoriesDto.getName());
        categories.setDescription(categoriesDto.getName());
        categories.setId(categoriesDto.getId());
        return categories;
    }

}
