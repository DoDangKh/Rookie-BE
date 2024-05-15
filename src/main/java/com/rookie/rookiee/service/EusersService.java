package com.rookie.rookiee.service;

import com.rookie.rookiee.dto.CredentialsDto;
import com.rookie.rookiee.dto.EusersDto;
import com.rookie.rookiee.dto.SignUpDto;
import com.rookie.rookiee.entity.Eusers;

public interface EusersService {

    EusersDto findbyEmail(String email);

    EusersDto login(CredentialsDto credentialsDto);

    EusersDto register(SignUpDto signUpDto);

    Eusers loadEusersByEmail(String email);

}
