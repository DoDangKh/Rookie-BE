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
        return new Eusers(
                eusersDto.getId(),
                eusersDto.getName(),
                eusersDto.getC_ID(),
                eusersDto.getPhone_num(),
                eusersDto.getEmail(),
                eusersDto.getDate_of_birth(),
                eusersDto.getAddress());
    }
}
