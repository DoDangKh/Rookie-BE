package com.rookie.rookiee.service;

import com.rookie.rookiee.dto.CategoriesDto;

public interface CategoriesService {

    CategoriesDto save(CategoriesDto categoriesDto);

    CategoriesDto findById(Long id);

    void deleteById(Long id);

    CategoriesDto updateCategories(CategoriesDto categoriesDto, Long id);
}
