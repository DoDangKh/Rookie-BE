package com.rookie.rookiee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.rookiee.config.UserAuthProvider;
import com.rookie.rookiee.dto.CredentialsDto;
import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.dto.RoleDto;
import com.rookie.rookiee.dto.SignUpDto;
import com.rookie.rookiee.service.EusersService;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired

    private ObjectMapper objectMapper;

    @MockBean
    private EusersService eusersService;

    @MockBean
    private UserAuthProvider userAuthProvider;

    @Test
    public void AuthController_Login_ReturnEusersDto() throws Exception {

        // ------------arrange-------------

        EusersDto eusersDto = EusersDto.builder()
                .name("Khoa")
                .address("HCM")
                .cID("123456789")
                .dateOfBirth(Instant.now())
                .email("ddangkhoa75@gmail.com")
                .build();

        CredentialsDto credentialsDto = CredentialsDto.builder()
                .email("ddangkhoa75@gmail.com")
                .password("123456")
                .build();

        // -------------act-----------------

        // when(eusersService.login(Mockito.any(CredentialsDto.class))).thenReturn(eusersDto);

        given(eusersService.login(ArgumentMatchers.any())).willReturn(eusersDto);

        // ---------------assert---------------------

        ResultActions response = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(credentialsDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void AuthController_Register_ReturnEusersDto() throws Exception {

        // ----------------arrange------------------

        Set<RoleDto> roleDto = new HashSet<>();
        roleDto.add(new RoleDto("ROLE_USER"));

        SignUpDto signUpDto = SignUpDto.builder()
                .name("Khoa")
                .address("HCM")
                .cID("123456789")
                .dateOfBirth(Instant.now())
                .email("ddangkho15@gmail.com")
                .password("123456")
                .build();
        signUpDto.setRoles(roleDto);

        EusersDto eusersDto = EusersDto.builder()
                .name("Khoa")
                .address("HCM")
                .cID("123456789")
                .dateOfBirth(Instant.now())
                .email("ddangkhoa75@gmail.com")
                .build();

        // ---------------- act -------------------------

        given(eusersService.register(ArgumentMatchers.any())).willReturn(eusersDto);

        // -------------- assert ------------------

        ResultActions response = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());

    }
}
