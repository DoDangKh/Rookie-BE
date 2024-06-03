package com.rookie.rookiee.controller;

import java.net.URI;

import org.apache.catalina.connector.Response;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookie.rookiee.dto.AddProductDto;
import com.rookie.rookiee.dto.CategoriesDto;
import com.rookie.rookiee.dto.PageProductDto;
import com.rookie.rookiee.dto.ProductsDto;
import com.rookie.rookiee.entity.Categories;
import com.rookie.rookiee.mapper.CategoriesMapper;
import com.rookie.rookiee.service.CategoriesService;
import com.rookie.rookiee.service.ProductsService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/product")
@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductsService productsService;

    private final CategoriesService categoriesService;

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ProductsDto> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(productsService.findById(Long.parseLong(id)));
    }

    @PostMapping("/add")
    public ResponseEntity<ProductsDto> add(@RequestBody AddProductDto productsDto) {

        System.out.println(productsDto);

        ProductsDto saved = productsService.save(productsDto);

        return ResponseEntity.created(URI.create("/api/v1/product/" + saved.getId())).body(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {

        productsService.deleteById(Long.parseLong(id));

        return ResponseEntity.ok().body("success");
    }

    @Transactional
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductsDto> putMethodName(@PathVariable String id, @RequestBody AddProductDto productsDto) {

        ProductsDto updated = productsService.update(productsDto, Long.parseLong(id));

        return ResponseEntity.created(URI.create("/api/v1/product/" + updated.getId())).body(updated);
    }

    @Transactional
    @GetMapping("/all")
    public ResponseEntity<List<ProductsDto>> getMethodName() {

        return ResponseEntity.ok().body(productsService.findAll());
    }

    @DeleteMapping("/delete/many")
    public ResponseEntity<String> deleteMany(@RequestBody List<Long> idList) {

        System.out.println(idList);

        productsService.deleteManyById(idList);

        return ResponseEntity.ok().body("Success");

    }

    @Transactional
    @GetMapping("/filter")
    public ResponseEntity<PageProductDto> getFilterProduct(@RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false) List<String> categoryids,
            @RequestParam(required = false) String minprice,
            @RequestParam(required = false) String maxprice,
            @RequestParam(required = false) Boolean feature) {

        Pageable paging = PageRequest.of(page, size);

        List<Long> idList = null;

        if (categoryids != null)
            idList = categoryids.stream().map(Long::parseLong).collect(Collectors.toList());

        Double min = null;

        Double max = null;

        if (minprice != null) {
            min = Double.parseDouble(minprice);
        }
        if (maxprice != null)
            max = Double.parseDouble(maxprice);

        return ResponseEntity.ok().body(productsService.findProduct(name,
                idList, min, max, feature, paging));
    }

}
