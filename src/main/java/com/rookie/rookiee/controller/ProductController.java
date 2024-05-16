package com.rookie.rookiee.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookie.rookiee.dto.ProductsDto;
import com.rookie.rookiee.service.ProductsService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public ResponseEntity<ProductsDto> postMethodName(@RequestBody ProductsDto productsDto) {

        ProductsDto saved = productsService.save(productsDto);

        return ResponseEntity.created(URI.create("/api/v1/product/" + saved.getId())).body(saved);
    }

}
