package com.rookie.rookiee.controller;

import java.net.URI;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookie.rookiee.dto.AddProductDto;
import com.rookie.rookiee.dto.ProductsDto;
import com.rookie.rookiee.service.ProductsService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/api/v1/product")
@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductsService productsService;

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

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductsDto> putMethodName(@PathVariable String id, @RequestBody ProductsDto productsDto) {

        ProductsDto updated = productsService.update(productsDto, Long.parseLong(id));

        return ResponseEntity.created(URI.create("/api/v1/product/" + updated.getId())).body(updated);
    }

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

}
