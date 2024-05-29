package com.rookie.rookiee.service.implemantion;

import java.util.ArrayList;
import java.util.List;

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

    private static final String NOT_FOUND_MESSAGE = "Category not found";

    @Override
    public CategoriesDto save(CategoriesDto categoriesDto) {
        System.out.println(categoriesDto.getDescription());

        Categories categories = CategoriesMapper.categoriesDtoToCategories(categoriesDto);

        System.out.println(categories.getDescription());

        return CategoriesMapper.toCategoriesDto(categoriesRepository.save(categories));

    }

    @Override
    public CategoriesDto findById(Long id) {

        Categories categories = categoriesRepository.findById(id)
                .orElseThrow(() -> new AppException(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND));

        return CategoriesMapper.toCategoriesDto(categories);
    }

    @Override
    public void deleteById(Long id) {

        Categories categories = categoriesRepository.findById(id)
                .orElseThrow(() -> new AppException(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND));

        categoriesRepository.delete(categories);

    }

    @Override
    public CategoriesDto updateCategories(CategoriesDto categoriesDto, Long id) {

        Categories categories = categoriesRepository.findById(id)
                .orElseThrow(() -> new AppException(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND));

        categories = CategoriesMapper.categoriesDtoToCategories(categoriesDto);

        categoriesRepository.save(categories);

        return CategoriesMapper.toCategoriesDto(categories);

    }

    @Override
    public List<CategoriesDto> findAll() {
        List<Categories> categories = categoriesRepository.findAll();
        List<CategoriesDto> categoriesDtos = new ArrayList<CategoriesDto>();
        for (Categories c : categories) {

            categoriesDtos.add(CategoriesMapper.toCategoriesDto(c));

        }

        return categoriesDtos;
    }

    @Override
    public void deleteMultiple(List<Long> idList) {

        System.out.println(idList);

        categoriesRepository.deleteAllById(idList);

    }

}
