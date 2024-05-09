package com.rookie.rookiee.mapper;

import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.dto.SignUpDto;
import com.rookie.rookiee.entity.Eusers;

public class EusersMapper {
    public static EusersDto maptoEusersDto(Eusers eusers) {
        return new EusersDto(
                eusers.getId(),
                eusers.getName(),
                eusers.getCID(),
                eusers.getPhoneNum(),
                eusers.getEmail(),
                eusers.getPassword(),
                eusers.getDateOfBirth(),
                eusers.getAddress(),
                null);
    }

    public static Eusers maptoEusers(EusersDto eusersDto) {
        return new Eusers(eusersDto.getId(),
                eusersDto.getName(),
                eusersDto.getCID(),
                eusersDto.getPhoneNum(),
                eusersDto.getEmail(),
                eusersDto.getPassword(),
                eusersDto.getDateOfBirth(),
                eusersDto.getAddress());
    }

    public static Eusers signUptoEusers(SignUpDto signUpDto) {
        Eusers eusers = new Eusers();
        eusers.setAddress(signUpDto.getAddress());
        eusers.setCID(signUpDto.getCID());
        eusers.setDateOfBirth(signUpDto.getDateOfBirth());
        eusers.setEmail(signUpDto.getEmail());
        eusers.setName(signUpDto.getName());
        eusers.setPassword(signUpDto.getPassword());
        eusers.setPhoneNum(signUpDto.getPhoneNum());
        return eusers;
    }
}
