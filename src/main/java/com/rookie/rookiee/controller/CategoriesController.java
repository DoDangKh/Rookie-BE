package com.rookie.rookiee.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rookie.rookiee.dto.CategoriesDto;
import com.rookie.rookiee.service.CategoriesService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoriesController {

    private final CategoriesService categoriesService;

    @PostMapping("/add")
    public ResponseEntity<CategoriesDto> postMethodName(@RequestBody CategoriesDto categoriesDto) {
        CategoriesDto saved = categoriesService.save(categoriesDto);

        return ResponseEntity.created(URI.create("/api/v1/category" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriesDto> getMethodName(@PathVariable("id") String id) {
        CategoriesDto categoriesDto = categoriesService.findById(Long.parseLong(id));
        return ResponseEntity.ok().body(categoriesDto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> requestMethodName(@PathVariable("id") String id) {

        categoriesService.deleteById(Long.parseLong(id));

        return ResponseEntity.ok().body("Delete Success");
    }

}
