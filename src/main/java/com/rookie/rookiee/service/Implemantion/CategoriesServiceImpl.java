package com.rookie.rookiee.service.implemantion;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rookie.rookiee.dto.CategoriesDto;
import com.rookie.rookiee.entity.Categories;
import com.rookie.rookiee.exception.AppException;
import com.rookie.rookiee.mapper.CategoriesMapper;
import com.rookie.rookiee.repository.CategoriesRepository;
import com.rookie.rookiee.service.CategoriesService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Override
    public CategoriesDto save(CategoriesDto categoriesDto) {

        Categories categories = CategoriesMapper.categoriesDtoToCategories(categoriesDto);

        return CategoriesMapper.toCategoriesDto(categoriesRepository.save(categories));

    }

    @Override
    public CategoriesDto findById(Long id) {

        Categories categories = categoriesRepository.findById(id)
                .orElseThrow(() -> new AppException("Category not found", HttpStatus.NOT_FOUND));

        return CategoriesMapper.toCategoriesDto(categories);
    }

    @Override
    public void deleteById(Long id) {

        Categories categories = categoriesRepository.findById(id)
                .orElseThrow(() -> new AppException("Category not found", HttpStatus.NOT_FOUND));

        categoriesRepository.delete(categories);

    }

}
