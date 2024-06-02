package com.rookie.rookiee.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookie.rookiee.service.RatesService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rookie.rookiee.dto.RatesDto;
import com.rookie.rookiee.exception.AppException;

@RequestMapping("api/v1/rates")
@RestController
@AllArgsConstructor
public class RatesController {

    private RatesService ratesService;

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<RatesDto> postMethodName(@RequestBody RatesDto ratesDto) {

        System.out.println(ratesDto);

        if (ratesService.findbyProductandUser(ratesDto.getIdproduct(), ratesDto.getIduser()) != null) {
            throw new AppException("Rates already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        RatesDto saved = ratesService.save(ratesDto, ratesDto.getIdproduct(), ratesDto.getIduser());

        return ResponseEntity.created(URI.create("/api/v1/rates/" + saved.getId())).body(saved);
    }

}
