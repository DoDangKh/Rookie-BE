package com.rookie.rookiee.mapper;

import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.dto.SignUpDto;
import com.rookie.rookiee.entity.Eusers;

public class EusersMapper {
    public static EusersDto maptoEusersDto(Eusers eusers) {
        return new EusersDto(
                eusers.getId(),
                eusers.getFirstName(),
                eusers.getLastName(),
                eusers.getEmail(),
                eusers.getPassword(),
                eusers.getModifiedUser(),
                null);
    }

    public static Eusers maptoEusers(EusersDto eusersDto) {
        Eusers eusers = new Eusers();

        eusers.setEmail(eusersDto.getEmail());
        eusers.setFirstName(eusersDto.getFirstName());
        eusers.setLastName(eusersDto.getLastName());
        eusers.setPassword(eusersDto.getPassword());
        return eusers;
    }

    public static Eusers signUptoEusers(SignUpDto signUpDto) {
        Eusers eusers = new Eusers();
        eusers.setEmail(signUpDto.getEmail());
        eusers.setFirstName(signUpDto.getFirstName());
        eusers.setLastName(signUpDto.getLastName());
        eusers.setPassword(signUpDto.getPassword());
        eusers.setRoles(null);
        return eusers;
    }
}
