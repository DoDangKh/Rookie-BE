package com.rookie.rookiee.service;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.nio.CharBuffer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rookie.config.TestConfig;
import com.rookie.rookiee.dto.CredentialsDto;
import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.dto.RoleDto;
import com.rookie.rookiee.dto.SignUpDto;
import com.rookie.rookiee.entity.Eusers;
import com.rookie.rookiee.exception.AppException;
import com.rookie.rookiee.repository.EusersRepository;
import com.rookie.rookiee.service.implemantion.EusersServiceImpl;

@Import({ TestConfig.class })
@ExtendWith(MockitoExtension.class)
public class EusersServiceTest {

        @Mock
        private EusersRepository eusersRepository;

        @Mock
        private PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

        @InjectMocks
        private EusersServiceImpl eusersService;

        @Test
        void EusersService_findbyEmail_ReturnsEusersDto() {
                // ------arrange--------
                Eusers eusers = Eusers.builder()
                                .firstName("Khoa")
                                .lastName("Do")
                                .email("ddangkhoa75@gmail.com")
                                .password("123456")
                                .build();

                // ---------------act----------------
                when(eusersRepository.findOneByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(eusers));
                EusersDto getEusers = eusersService.findbyEmail("ddangkhoa75@gmail.com");

                // -----------------assert---------------
                Assertions.assertThat(getEusers).isNotNull();

        }

        @Test
        void EusersService_login_returnEusersDto() {
                // ----------arrange--------------

                CredentialsDto credentialsDto = CredentialsDto.builder()
                                .email("ddangkhoa75@gmail.com")
                                .password("123456")
                                .build();

                CredentialsDto failedDto = CredentialsDto.builder()
                                .email("ddangkhoa75@gmail.com")
                                .password("123")
                                .build();

                Eusers eusers = Eusers.builder()
                                .firstName("Khoa")
                                .lastName("Do")
                                .email("ddangkhoa75@gmail.com")
                                .password("123456")
                                .build();

                // ------------act----------------

                // System.out.println("CHECK:" + passwordEncoder.matches("123456", "123456"));

                when(eusersRepository.findOneByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(eusers));
                // when(passwordEncoder.matches(any, null))
                when(passwordEncoder.matches(Mockito.any(CharSequence.class), Mockito.anyString())).thenReturn(true);
                when(passwordEncoder.matches(CharBuffer.wrap(failedDto.getPassword()), "123456"))
                                .thenReturn(false);

                EusersDto getEusers = eusersService.login(credentialsDto);

                Exception exception = assertThrows(AppException.class,
                                () -> {
                                        eusersService.login(failedDto);
                                });

                // -----------assert-----------------

                Assertions.assertThat(getEusers).isNotNull();
                Assertions.assertThat(exception.getMessage()).isEqualTo("Invalid password");
        }

        @Test
        void EusersSErvice_register_returnEusersDto() {
                // --------------arrange----------------------
                Set<RoleDto> roleDto = new HashSet<>();
                roleDto.add(new RoleDto("ROLE_USER"));

                SignUpDto signUpDto = SignUpDto.builder()
                                .firstName("Khoa")
                                .lastName("Do")
                                .email("ddangkhoa15@gmail.com")
                                .password("123456")
                                .build();
                signUpDto.setRoles(roleDto);

                SignUpDto existUsers = SignUpDto.builder()
                                .firstName("Khoa")
                                .lastName("Do")
                                .email("ddangkhoa75@gmail.com")
                                .password("123456")
                                .build();
                existUsers.setRoles(roleDto);

                Eusers eusers = Eusers.builder()
                                .firstName("Khoa")
                                .lastName("Do")
                                .email("ddangkhoa75@gmail.com")
                                .password("123456")
                                .build();

                // -------------act--------------

                when(eusersRepository.findOneByEmail(anyString())).thenReturn(Optional.empty());

                when(eusersRepository.findOneByEmail("ddangkhoa75@gmail.com")).thenReturn(Optional.ofNullable(eusers));

                when(eusersRepository.save(Mockito.any(Eusers.class))).thenReturn(eusers);

                Exception exception = assertThrows(AppException.class, () -> {
                        eusersService.register(existUsers);
                });

                EusersDto savedEusers = eusersService.register(signUpDto);

                // -----------assert----------------

                // Assertions.assertThat(eusersRepository.findByEmail(signUpDto.getEmail())).isNotNull();

                Assertions.assertThat(savedEusers).isNotNull();

                Assertions.assertThat(exception.getMessage()).isEqualTo("Email already exists");

                // Assertions.assertThat(exception.get).isEqualTo("Email already exists")
        }

        @Test
        void EusersService_findAll_ReturnList() {
                // ------------arrange-------------------
                Eusers eusers = Eusers.builder()
                                .firstName("Khoa")
                                .lastName("Do")
                                .email("ddangkhoa75@gmail.com")
                                .password("123456")
                                .build();
                List<Eusers> listEusers = new ArrayList<>();
                listEusers.add(eusers);

                // --------------act----------------------

                when(eusersRepository.findAll()).thenReturn(listEusers);

                List<EusersDto> eusersDtos = eusersService.findAll();

                // -------------assert---------------------

                Assertions.assertThat(eusersDtos).isNotEmpty();

        }

}