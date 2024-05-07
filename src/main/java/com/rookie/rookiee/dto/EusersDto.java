package com.rookie.rookiee.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EusersDto {
    private Long id;

    private String Name;

    @JsonProperty("c-id")
    private String C_ID;

    @JsonProperty("phone-num")
    private String Phone_num;

    private String Email;

    @JsonProperty("date-of-birth")
    private Instant Date_of_birth;

    private String address;

}
