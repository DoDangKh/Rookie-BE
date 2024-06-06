package com.rookie.rookiee.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rookie.rookiee.dto.CategoriesDto;
import com.rookie.rookiee.service.CategoriesService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = { "http://localhost:3000" })
public class CategoriesController {

    private final CategoriesService categoriesService;

    @PostMapping("/add")
    public ResponseEntity<CategoriesDto> add(@RequestBody CategoriesDto categoriesDto) {

        CategoriesDto saved = categoriesService.save(categoriesDto);

        return ResponseEntity.created(URI.create("/api/v1/category" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriesDto> get(@PathVariable("id") String id) {
        CategoriesDto categoriesDto = categoriesService.findById(Long.parseLong(id));
        return ResponseEntity.ok().body(categoriesDto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> requestMethodName(@PathVariable("id") String id) {

        categoriesService.deleteById(Long.parseLong(id));

        return ResponseEntity.ok().body("Delete Success");
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable String id) {
        // TODO: process PUT request

        categoriesService.activeById(Long.parseLong(id));

        return ResponseEntity.ok().body("Success");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CategoriesDto> putMethodName(@PathVariable String id,
            @RequestBody CategoriesDto categoriesDto) {
        System.out.println(categoriesDto);

        CategoriesDto updated = categoriesService.updateCategories(categoriesDto, Long.parseLong(id));

        return ResponseEntity.created(URI.create("/api/v1/category" + updated.getId()))
                .body(updated);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoriesDto>> getAll() {

        List<CategoriesDto> categoriesDtos = categoriesService.findAll();

        return ResponseEntity.ok().body(categoriesDtos);
    }

    @DeleteMapping("/delete/many")
    public ResponseEntity<String> deleteMany(@RequestBody List<Long> idList) {

        System.out.println(idList);

        categoriesService.deleteMultiple(idList);

        return ResponseEntity.ok().body("Delete Success");

    }

    @GetMapping("/active")
    public ResponseEntity<List<CategoriesDto>> getActive() {
        return ResponseEntity.ok().body(categoriesService.findActive());
    }

}
