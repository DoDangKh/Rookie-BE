package com.rookie.rookiee.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rookie.rookiee.dto.CategoriesDto;
import com.rookie.rookiee.entity.Categories;
import com.rookie.rookiee.exception.AppException;
import com.rookie.rookiee.repository.CategoriesRepository;
import com.rookie.rookiee.service.implemantion.CategoriesServiceImpl;

@ExtendWith(MockitoExtension.class)
class CategoriesServiceTest {

    @Mock
    CategoriesRepository categoriesRepository;

    @InjectMocks
    CategoriesServiceImpl categoriesService;

    @Test
    void CategoriesService_save_returnCategoriesDto() {

        // -------arrange--------------------
        Categories categories = Categories.builder()
                .description("this is Description")
                .name("Categories4")
                .build();

        CategoriesDto categoriesdDto = CategoriesDto.builder()
                .description("this is Description")
                .name("Categories4")
                .build();

        // ---------- action ------------

        when(categoriesRepository.save(Mockito.any(Categories.class))).thenReturn(categories);

        CategoriesDto saved = categoriesService.save(categoriesdDto);

        // -----------assert------------------

        Assertions.assertThat(saved).isNotNull();

    }

    @Test
    void CategoriesService_findById_returnCategoriesDto() {

        // -------arrange--------------------
        Categories categories = Categories.builder()
                .description("this is Description")
                .name("Categories4")
                .build();

        Long id = (long) 1;

        // ----------action------------------

        when(categoriesRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(categories));

        CategoriesDto categoriesDto = categoriesService.findById(id);

        // --------------assert-------------

        Assertions.assertThat(categoriesDto).isNotNull();

    }

    @Test
    void CategoriesService_save_ReturnNotFoundException() {

        // -------------arrange----------------

        Long id = (long) 1;

        // -------------action-----------------

        when(categoriesRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));

        Exception exception = assertThrows(AppException.class, () -> {
            categoriesService.findById(id);
        });

        // --------------assert-----------------

        Assertions.assertThat(exception.getMessage()).isEqualTo("Category not found");

    }

    @Test
    void CategoriesService_deleteById_Success() {

        // -------arrange--------------------
        Categories categories = Categories.builder()
                .description("this is Description")
                .name("Categories4")
                .build();

        Long id = (long) 1;

        // --------act-----------------------

        when(categoriesRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(categories));

        // ----------assert------------------

        assertAll(() -> categoriesService.deleteById(id));

    }

    @Test
    void CategoriesService_deleteById_ReturnNotFoundException() {

        // ------------arrange------------------------

        Long id = (long) 1;

        // -----------act-----------------------------
        when(categoriesRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));

        Exception exception = assertThrows(AppException.class, () -> {
            categoriesService.deleteById(id);
        });

        // -------------assert------------------------

        Assertions.assertThat(exception.getMessage()).isEqualTo("Category not found");

    }

    @Test
    void CategoriesService_update_ReturnCategoriesDto() {
        // -------------arrange-------------------------

        Categories categories = Categories.builder()
                .description("this is Description")
                .name("Categories4")
                .build();

        CategoriesDto categoriesdDto = CategoriesDto.builder()
                .description("this is Description")
                .name("Categories4")
                .build();

        Long id = (long) 1;

        // -----------------act-------------------------

        when(categoriesRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(categories));

        when(categoriesRepository.save(Mockito.any(Categories.class))).thenReturn(categories);

        CategoriesDto updated = categoriesService.updateCategories(categoriesdDto, id);

        // -----------------assert----------------------

        Assertions.assertThat(updated).isNotNull();
    }

    @Test
    void CategoriesService_update_ReturnNotFoundException() {

        // -------------arrange---------------------

        Long id = (long) 1;

        CategoriesDto categoriesdDto = CategoriesDto.builder()
                .description("this is Description")
                .name("Categories4")
                .build();

        // ----------------------act-----------------

        when(categoriesRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));

        Exception exception = assertThrows(AppException.class, () -> {
            categoriesService.updateCategories(categoriesdDto, id);
        });

        // ---------------------assert----------------------

        Assertions.assertThat(exception.getMessage()).isEqualTo("Category not found");

    }

}
