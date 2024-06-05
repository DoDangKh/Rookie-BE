package com.rookie.rookiee.mapper;

import java.time.format.DateTimeFormatter;
import java.util.Set;

import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.dto.SignUpDto;
import com.rookie.rookiee.entity.Eusers;
import com.rookie.rookiee.entity.Role;

public class EusersMapper {
    public static EusersDto maptoEusersDto(Eusers eusers) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println(eusers.getDateCreated());

        EusersDto eusersDto = new EusersDto();
        eusersDto.setCreatedAt(dateTimeFormatter.format(eusers.getDateCreated()));
        eusersDto.setEmail(eusers.getEmail());
        eusersDto.setFirstName(eusers.getFirstName());
        eusersDto.setId(eusers.getId());
        eusersDto.setLastName(eusers.getLastName());
        eusersDto.setModifiedUser(eusers.getModifiedUser());
        eusersDto.setPassword(eusers.getPassword());
        eusersDto.setUpdatedAt(dateTimeFormatter.format(eusers.getDateModified()));
        Set<Role> roles = eusers.getRoles();
        eusersDto.setRole(roles.iterator().next().getRolename());
        return eusersDto;
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
