package com.rookie.rookiee.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rookie.rookiee.config.UserAuthProvider;
import com.rookie.rookiee.dto.CredentialsDto;
import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.dto.RoleDto;
import com.rookie.rookiee.dto.SignUpDto;
import com.rookie.rookiee.service.EusersService;

import lombok.AllArgsConstructor;
import java.net.URI;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final EusersService eusersService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<EusersDto> login(@RequestBody CredentialsDto credentialsDto) {

        EusersDto eusers = eusersService.login(credentialsDto);
        eusers.setToken(userAuthProvider.createToken(eusers.getEmail()));
        return ResponseEntity.ok(eusers);
    }

    @PostMapping("/register")
    public ResponseEntity<EusersDto> register(@RequestBody SignUpDto signUpDto) {
        RoleDto roleDto = new RoleDto("ROLE_USER");
        signUpDto.setRoles(Set.of(roleDto));
        EusersDto eusers = eusersService.register(signUpDto);
        eusers.setToken(userAuthProvider.createToken(eusers.getEmail()));
        return ResponseEntity.created(URI.create("/users/" + eusers.getId())).body(eusers);
    }

}
