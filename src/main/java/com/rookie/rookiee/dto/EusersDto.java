package com.rookie.rookiee.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    private String name;

    @JsonProperty("c-id")
    private String cID;

    @JsonProperty("phone-num")
    private String phoneNum;

    private String email;

    private String password;

    @JsonProperty("date-of-birth")
    private Instant dateOfBirth;

    private String address;

    @EqualsAndHashCode.Exclude
    private String token;

}
