package com.rookie.rookiee.service;

import com.rookie.rookiee.dto.CategoriesDto;
import java.util.List;

public interface CategoriesService {

    CategoriesDto save(CategoriesDto categoriesDto);

    CategoriesDto findById(Long id);

    void deleteById(Long id);

    void activeById(Long id);

    CategoriesDto updateCategories(CategoriesDto categoriesDto, Long id);

    List<CategoriesDto> findAll();

    List<CategoriesDto> findActive();

    void deleteMultiple(List<Long> idList);
}
