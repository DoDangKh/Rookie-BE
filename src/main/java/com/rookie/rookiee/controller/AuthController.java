package com.rookie.rookiee.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rookie.rookiee.config.UserAuthProvider;
import com.rookie.rookiee.dto.CredentialsDto;
import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.dto.SignUpDto;
import com.rookie.rookiee.service.EusersService;

import lombok.AllArgsConstructor;
import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
public class AuthController {

    private final EusersService eusersService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<EusersDto> login(@RequestBody CredentialsDto credentialsDto) {
        EusersDto eusers = eusersService.login(credentialsDto);
        eusers.setToken(userAuthProvider.createToken(eusers.getEmail()));
        return ResponseEntity.ok(eusers);
    }

    @PostMapping("/login")
    public ResponseEntity<EusersDto> login(@RequestBody SignUpDto signUpDto) {
        EusersDto eusers = eusersService.register(signUpDto);
        eusers.setToken(userAuthProvider.createToken(eusers.getEmail()));
        return ResponseEntity.created(URI.create("/users/" + eusers.getId())).body(eusers);
    }

}
