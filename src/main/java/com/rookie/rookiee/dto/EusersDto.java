package com.rookie.rookiee.dto;

import java.sql.Date;

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
    private String C_ID;
    private String Phone_num;
    private String Email;
    private Date Date_of_birth;
    private String address;
}
