package com.rookie.rookiee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.entity.Eusers;
import com.rookie.rookiee.service.EusersService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/eusers")
public class EusersController {

    private EusersService eusersService;

    // private AccountService accountService;

    // @PostMapping("/create")
    // public ResponseEntity<EusersDto> createEuser(@RequestBody EusersDto
    // eusersDto) {
    // System.out.println("Controller:" + eusersDto.getName());
    // // eusersService.createEusers(eusersDto);
    // EusersDto savedEusersDto = eusersService.createEusers(eusersDto);
    // return new ResponseEntity<>(savedEusersDto, HttpStatus.CREATED);
    // }

    // @GetMapping("/account")
    // public ResponseEntity<AccountDto> getMethodName() {
    // AccountDto getaccount = accountService.getAccountByUsername("KhoaDo");
    // return new ResponseEntity<>(getaccount, HttpStatus.ACCEPTED);
    // }

    @GetMapping("/all")
    public ResponseEntity<List<EusersDto>> getMethodName() {
        return ResponseEntity.ok().body(eusersService.findAll());
    }

}
