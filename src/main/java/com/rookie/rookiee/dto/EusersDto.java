package com.rookie.rookiee.dto;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@EqualsAndHashCode
public class EusersDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String modifiedUser;

    private String createdAt;

    private String updatedAt;

    private String role;

    @EqualsAndHashCode.Exclude
    private String token;

}
