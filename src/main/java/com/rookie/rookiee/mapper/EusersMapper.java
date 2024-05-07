package com.rookie.rookiee.mapper;

import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.entity.Eusers;

public class EusersMapper {
    public static EusersDto maptoEusersDto(Eusers eusers) {
        return new EusersDto(
                eusers.getId(),
                eusers.getName(),
                eusers.getC_ID(),
                eusers.getPhone_num(),
                eusers.getEmail(),
                eusers.getDate_of_birth(),
                eusers.getAddress());
    }

    public static Eusers maptoEusers(EusersDto eusersDto) {
        Eusers temp = new Eusers();
        temp.setId(eusersDto.getId());
        temp.setName(eusersDto.getName());
        temp.setC_ID(eusersDto.getC_ID());
        temp.setPhone_num(eusersDto.getPhone_num());
        temp.setEmail(eusersDto.getEmail());
        temp.setDate_of_birth(eusersDto.getDate_of_birth());
        temp.setAddress(eusersDto.getAddress());
        return temp;
    }
}
