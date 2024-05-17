package com.rookie.rookiee.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.rookiee.dto.CategoriesDto;
import com.rookie.rookiee.service.CategoriesService;

@WebMvcTest(controllers = CategoriesController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CategoriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoriesService categoriesService;

    @Test
    void CategoriesController_add_ReturnCreated() throws JsonProcessingException, Exception {

        // --------------arrange-------------------

        CategoriesDto categoriesDto = CategoriesDto.builder()
                .id((long) 1)
                .name("category 1")
                .description("this is description")
                .build();

        // --------------action----------------

        given(categoriesService.save(Mockito.any(CategoriesDto.class))).willReturn(categoriesDto);

        // --------------assert--------------------

        ResultActions response = mockMvc.perform(post("/api/v1/category/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoriesDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        CoreMatchers.is(categoriesDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description",
                        CoreMatchers.is(categoriesDto.getDescription())));

    }

    @Test
    void CategoriesController_get_ReturnOk() throws Exception {

        // -----------arrange----------------------

        CategoriesDto categoriesDto = CategoriesDto.builder()
                .id((long) 1)
                .name("category 1")
                .description("this is description")
                .build();

        // -------------------action---------------

        given(categoriesService.findById(ArgumentMatchers.any())).willReturn(categoriesDto);

        ResultActions response = mockMvc.perform(get("/api/v1/category/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoriesDto)));

        // ---------------------assert-------------------

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        CoreMatchers.is(categoriesDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description",
                        CoreMatchers.is(categoriesDto.getDescription())));

    }

    @Test
    void CategoriesController_update_ReturnOk() throws Exception {

        // --------------------------arrange----------------------

        CategoriesDto categoriesDto = CategoriesDto.builder()
                .id((long) 1)
                .name("category 1")
                .description("this is description")
                .build();

        // -------------------action---------------

        given(categoriesService.updateCategories(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .willReturn(categoriesDto);

        ResultActions response = mockMvc.perform(put("/api/v1/category/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoriesDto)));

        // ----------------assert-------------------

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        CoreMatchers.is(categoriesDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description",
                        CoreMatchers.is(categoriesDto.getDescription())));
    }

    @Test
    void CategoriesController_delete_ReturnOk() throws Exception {

        // --------------------------arrange----------------------

        // -------------------action---------------

        doNothing().when(categoriesService).deleteById((long) 1);

        ResultActions response = mockMvc.perform(delete("/api/v1/category/delete/1")
                .contentType(MediaType.APPLICATION_JSON));

        // ----------------assert-------------------

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
