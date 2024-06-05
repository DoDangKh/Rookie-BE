package com.rookie.rookiee.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rookie.rookiee.dto.CartsDto;

import com.rookie.rookiee.service.CartsService;

import lombok.AllArgsConstructor;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carts")
@CrossOrigin(origins = { "http://localhost:3000" })
public class CartsController {

    private final CartsService cartsService;

    @PostMapping("/add")
    public ResponseEntity<CartsDto> addToCarts(@RequestBody CartsDto cartsDto) {

        CartsDto cartsDto2 = cartsService.addToCarts(cartsDto);

        return ResponseEntity.created(URI.create("/api/v1/Carts" + cartsDto2.getId())).body(cartsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CartsDto>> getMethodName(@PathVariable("id") String id) {

        List<CartsDto> cartsDtos = cartsService.findByUserId(Long.parseLong(id));

        return ResponseEntity.ok().body(cartsDtos);

    }

    @PutMapping("update/{id}")
    public ResponseEntity<CartsDto> putMethodName(@PathVariable String id, @RequestBody CartsDto cartsDto) {

        return ResponseEntity.ok().body(cartsService.updateCarts(cartsDto, Long.parseLong(id)));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCarts(@PathVariable String id) {

        cartsService.DeleteCarts(Long.parseLong(id));

        return ResponseEntity.ok().body("Success");
    }
}
