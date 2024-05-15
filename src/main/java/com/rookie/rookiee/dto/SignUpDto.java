package com.rookie.rookiee.dto;

import java.time.Instant;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private String name;
    @JsonProperty("cID")
    private String cID;
    private String phoneNum;
    private String email;
    private String password;
    private Instant dateOfBirth;
    private String address;
    private Set<RoleDto> roles;
}
